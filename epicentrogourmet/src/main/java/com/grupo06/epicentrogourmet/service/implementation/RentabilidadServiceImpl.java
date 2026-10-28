package com.grupo06.epicentrogourmet.service.implementation;

import com.grupo06.epicentrogourmet.dto.RentabilidadDTO;
import com.grupo06.epicentrogourmet.entity.ItemPedido;
import com.grupo06.epicentrogourmet.entity.Pedido;
import com.grupo06.epicentrogourmet.entity.UnidadVenta;
import com.grupo06.epicentrogourmet.repository.IItemPedidoRepository;
import com.grupo06.epicentrogourmet.repository.IPedidoRepository;
import com.grupo06.epicentrogourmet.repository.UnidadVentaRepository;
import com.grupo06.epicentrogourmet.service.interfaces.IRentabilidadService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.grupo06.epicentrogourmet.entity.AsignacionEmpleado;
import com.grupo06.epicentrogourmet.entity.CostosFestival;
import com.grupo06.epicentrogourmet.entity.Empleado;
import com.grupo06.epicentrogourmet.entity.Festival;
import com.grupo06.epicentrogourmet.repository.AsignacionEmpleadoRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class RentabilidadServiceImpl implements IRentabilidadService {

    private final IPedidoRepository pedidoRepository;
    private final IItemPedidoRepository itemPedidoRepository;
    private final UnidadVentaRepository unidadVentaRepository;
    private final AsignacionEmpleadoRepository asignacionEmpleadoRepository;

    public RentabilidadServiceImpl(IPedidoRepository pedidoRepository, 
                                   IItemPedidoRepository itemPedidoRepository, 
                                   UnidadVentaRepository unidadVentaRepository,
                                   AsignacionEmpleadoRepository asignacionEmpleadoRepository) {
    	
        this.pedidoRepository = pedidoRepository;
        this.itemPedidoRepository = itemPedidoRepository;
        this.unidadVentaRepository = unidadVentaRepository;
        this.asignacionEmpleadoRepository = asignacionEmpleadoRepository;

    }

    @Override
    @Transactional(readOnly = true)
    public RentabilidadDTO calcularRentabilidad(Long idUnidadVenta, LocalDate fechaDesde, LocalDate fechaHasta) {
        UnidadVenta unidad = unidadVentaRepository.findById(idUnidadVenta)
                .orElseThrow(() -> new IllegalArgumentException("Unidad de venta no encontrada."));

        // Buscamos los pedidos en ese rango de fechas
        List<Pedido> pedidos = pedidoRepository.findByUnidadVenta_IdUnidadVentaAndFechaBetween(idUnidadVenta, fechaDesde, fechaHasta);

        BigDecimal ingresosTotales = BigDecimal.ZERO;
        BigDecimal costosPlatosTotales = BigDecimal.ZERO;

        // Recorremos los pedidos y sumamos la plata de cada plato
        for (Pedido pedido : pedidos) {
            List<ItemPedido> items = itemPedidoRepository.findByPedido_IdPedido(pedido.getIdPedido());
            for (ItemPedido item : items) {
                BigDecimal cantidad = new BigDecimal(item.getCantidad());
                
                // Ingreso = cantidad * precio_venta
                BigDecimal ingresoItem = item.getPlato().getPrecioVenta().multiply(cantidad);
                ingresosTotales = ingresosTotales.add(ingresoItem);
                
                // Costo = cantidad * costo_produccion
                BigDecimal costoItem = item.getPlato().getCostoProduccion().multiply(cantidad);
                costosPlatosTotales = costosPlatosTotales.add(costoItem);
            }
        }

        // Calculamos los otros costos obligatorios (Canon y Sueldos)
        BigDecimal totalSueldos = calcularSueldos(unidad);
        BigDecimal canon = calcularCanon(unidad);

        // Armamos el ticket (DTO)
        RentabilidadDTO dto = new RentabilidadDTO();
        dto.setNombreUnidad(unidad.getNombreComercial());
        dto.setIngresosPedidos(ingresosTotales);
        dto.setCostosPlatos(costosPlatosTotales);
        dto.setCostosSueldos(totalSueldos);
        dto.setCostoCanon(canon);

        // FORMULA FINAL: Ingresos - Costo Platos - Sueldos - Canon
        BigDecimal rentabilidadNeta = ingresosTotales
                .subtract(costosPlatosTotales)
                .subtract(totalSueldos)
                .subtract(canon);
        
        dto.setRentabilidadNeta(rentabilidadNeta);

        return dto;
    }

    private BigDecimal calcularCanon(UnidadVenta unidad) {
        if (unidad.getFestival() == null
                || unidad.getFestival().getCostos() == null) {
            throw new IllegalStateException(
                    "La unidad no tiene costos de festival configurados."
            );
        }

        return unidad.calcularCanon(
                unidad.getFestival().getCostos()
        );
    }
    
    private BigDecimal calcularSueldos(UnidadVenta unidad) {
        Festival festival = unidad.getFestival();

        if (festival == null || festival.getCostos() == null) {
            throw new IllegalStateException(
                    "La unidad no tiene un festival con costos configurados."
            );
        }

        CostosFestival costos = festival.getCostos();

        return asignacionEmpleadoRepository
                .findByUnidadVenta(unidad)
                .stream()
                .filter(asignacion -> asignacion.getFechaDesde() != null)
                .filter(asignacion ->
                        !asignacion.getFechaDesde().isAfter(festival.getFechaFin()))
                .filter(asignacion ->
                        asignacion.getFechaHasta() == null
                        || !asignacion.getFechaHasta().isBefore(festival.getFechaInicio()))
                .map(asignacion -> {
                    BigDecimal sueldo = asignacion.getSueldoLiquidado();

                    if (sueldo != null) {
                        return sueldo;
                    }

                    Empleado empleado = asignacion.getEmpleado();

                    if (empleado == null) {
                        throw new IllegalStateException(
                                "Una asignación no tiene un empleado asociado."
                        );
                    }

                    return empleado.liquidarHaberes(festival, costos);
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}