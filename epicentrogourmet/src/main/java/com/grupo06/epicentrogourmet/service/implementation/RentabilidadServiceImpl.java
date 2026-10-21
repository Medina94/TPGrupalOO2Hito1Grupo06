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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class RentabilidadServiceImpl implements IRentabilidadService {

    private final IPedidoRepository pedidoRepository;
    private final IItemPedidoRepository itemPedidoRepository;
    private final UnidadVentaRepository unidadVentaRepository;

    public RentabilidadServiceImpl(IPedidoRepository pedidoRepository, 
                                   IItemPedidoRepository itemPedidoRepository, 
                                   UnidadVentaRepository unidadVentaRepository) {
        this.pedidoRepository = pedidoRepository;
        this.itemPedidoRepository = itemPedidoRepository;
        this.unidadVentaRepository = unidadVentaRepository;
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

    // ==========================================================
    // MÉTODOS PARA QUE VOS PONGAS LA LÓGICA EXACTA DE TU GRUPO
    // ==========================================================

    private BigDecimal calcularCanon(UnidadVenta unidad) {
        // TODO: Reemplazar con la lógica real de tu canon. 
        // Ejemplo: retornar unidad.getCanon() si tuvieran ese método.
        return new BigDecimal("50000"); // Valor de prueba
    }

    private BigDecimal calcularSueldos(UnidadVenta unidad) {
        // TODO: Buscar los empleados de esta unidad y sumar sus sueldos.
        return new BigDecimal("120000"); // Valor de prueba
    }
}