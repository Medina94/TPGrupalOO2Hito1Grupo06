package com.grupo06.epicentrogourmet.service.implementation;

import com.grupo06.epicentrogourmet.dto.RentabilidadDTO;

import com.grupo06.epicentrogourmet.entity.ItemPedido;
import com.grupo06.epicentrogourmet.entity.Pedido;
import com.grupo06.epicentrogourmet.entity.UnidadVenta;
import com.grupo06.epicentrogourmet.entity.CostosFestival;
import com.grupo06.epicentrogourmet.entity.Empleado;
import com.grupo06.epicentrogourmet.entity.Festival;

import com.grupo06.epicentrogourmet.repository.IItemPedidoRepository;
import com.grupo06.epicentrogourmet.repository.IPedidoRepository;
import com.grupo06.epicentrogourmet.repository.UnidadVentaRepository;
import com.grupo06.epicentrogourmet.repository.AsignacionEmpleadoRepository;

import com.grupo06.epicentrogourmet.service.interfaces.IRentabilidadService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class RentabilidadServiceImpl implements IRentabilidadService {

    private final IPedidoRepository pedidoRepository;
    private final IItemPedidoRepository itemPedidoRepository;
    private final UnidadVentaRepository unidadVentaRepository;
    private final AsignacionEmpleadoRepository asignacionEmpleadoRepository;

    public RentabilidadServiceImpl(
            IPedidoRepository pedidoRepository,
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
    public RentabilidadDTO calcularRentabilidad(
            Long idUnidadVenta,
            LocalDate fechaDesde,
            LocalDate fechaHasta) {

        // Validamos las fechas ingresadas.
        if (fechaDesde == null || fechaHasta == null) {
            throw new IllegalArgumentException(
                    "Ambas fechas son obligatorias."
            );
        }

        if (fechaDesde.isAfter(fechaHasta)) {
            throw new IllegalArgumentException(
                    "La fecha desde no puede ser posterior a la fecha hasta."
            );
        }

        // Buscamos la unidad de venta.
        UnidadVenta unidad = unidadVentaRepository.findById(idUnidadVenta)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Unidad de venta no encontrada."
                        )
                );

        Festival festival = unidad.getFestival();

        // Validamos los datos del festival.
        if (festival == null
                || festival.getCostos() == null
                || festival.getFechaInicio() == null
                || festival.getFechaFin() == null) {

            throw new IllegalStateException(
                    "La unidad no tiene un festival con costos y fechas configurados."
            );
        }

        if (festival.getFechaInicio().isAfter(festival.getFechaFin())) {
            throw new IllegalStateException(
                    "Las fechas del festival no son válidas."
            );
        }

        // Determinamos los días que coinciden entre el período
        // consultado y las fechas del festival.
        LocalDate inicioPeriodo = fechaDesde.isAfter(festival.getFechaInicio())
                ? fechaDesde
                : festival.getFechaInicio();

        LocalDate finPeriodo = fechaHasta.isBefore(festival.getFechaFin())
                ? fechaHasta
                : festival.getFechaFin();

        if (inicioPeriodo.isAfter(finPeriodo)) {
            throw new IllegalArgumentException(
                    "El período consultado no coincide con las fechas del festival."
            );
        }

        // Calculamos los días consultados, incluyendo ambas fechas.
        long diasConsultados = ChronoUnit.DAYS.between(
                inicioPeriodo,
                finPeriodo
        ) + 1;

        // Calculamos los días totales del festival, incluyendo ambas fechas.
        long diasFestival = ChronoUnit.DAYS.between(
                festival.getFechaInicio(),
                festival.getFechaFin()
        ) + 1;

        // Obtenemos el canon base correspondiente a la unidad
        // para el festival completo.
        BigDecimal canonTotalFestival = unidad.calcularCanon(
                festival.getCostos()
        );

        // Calculamos el canon proporcional a los días consultados.
        BigDecimal canonProporcional = canonTotalFestival
                .multiply(BigDecimal.valueOf(diasConsultados))
                .divide(
                        BigDecimal.valueOf(diasFestival),
                        2,
                        RoundingMode.HALF_UP
                );

        // Buscamos los pedidos de la unidad dentro del período seleccionado.
        List<Pedido> pedidos = pedidoRepository
                .findByUnidadVenta_IdUnidadVentaAndFechaBetween(
                        idUnidadVenta,
                        fechaDesde,
                        fechaHasta
                );

        BigDecimal ingresosTotales = BigDecimal.ZERO;
        BigDecimal costosPlatosTotales = BigDecimal.ZERO;

        // Recorremos los pedidos y calculamos ingresos y costos de producción.
        for (Pedido pedido : pedidos) {

            List<ItemPedido> items =
                    itemPedidoRepository.findByPedido_IdPedido(
                            pedido.getIdPedido()
                    );

            for (ItemPedido item : items) {

                BigDecimal cantidad =
                        BigDecimal.valueOf(item.getCantidad());

                // Ingreso = cantidad * precio de venta.
                BigDecimal ingresoItem =
                        item.getPlato().getPrecioVenta()
                                .multiply(cantidad);

                ingresosTotales =
                        ingresosTotales.add(ingresoItem);

                // Costo = cantidad * costo de producción.
                BigDecimal costoItem =
                        item.getPlato().getCostoProduccion()
                                .multiply(cantidad);

                costosPlatosTotales =
                        costosPlatosTotales.add(costoItem);
            }
        }

        // Calculamos los sueldos de los empleados.
        BigDecimal totalSueldos = calcularSueldos(unidad);

        // Armamos el DTO con los resultados.
        RentabilidadDTO dto = new RentabilidadDTO();

        dto.setNombreUnidad(unidad.getNombreComercial());

        dto.setIngresosPedidos(ingresosTotales);

        dto.setCostosPlatos(costosPlatosTotales);

        dto.setCostosSueldos(totalSueldos);

        // Guardamos tanto el canon base como el proporcional.
        dto.setCanonTotalFestival(canonTotalFestival);
        dto.setCostoCanon(canonProporcional);

        // Guardamos los días utilizados para explicar el cálculo.
        dto.setDiasConsultados(diasConsultados);
        dto.setDiasFestival(diasFestival);

        // Rentabilidad neta = ingresos - costos.
        BigDecimal rentabilidadNeta = ingresosTotales
                .subtract(costosPlatosTotales)
                .subtract(totalSueldos)
                .subtract(canonProporcional);

        dto.setRentabilidadNeta(rentabilidadNeta);

        return dto;
    }

    /**
     * Calcula los sueldos de los empleados asignados a la unidad.
     */
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
                .filter(asignacion ->
                        asignacion.getFechaDesde() != null
                )
                .filter(asignacion ->
                        !asignacion.getFechaDesde().isAfter(
                                festival.getFechaFin()
                        )
                )
                .filter(asignacion ->
                        asignacion.getFechaHasta() == null
                        || !asignacion.getFechaHasta().isBefore(
                                festival.getFechaInicio()
                        )
                )
                .map(asignacion -> {

                    BigDecimal sueldo =
                            asignacion.getSueldoLiquidado();

                    if (sueldo != null) {
                        return sueldo;
                    }

                    Empleado empleado =
                            asignacion.getEmpleado();

                    if (empleado == null) {
                        throw new IllegalStateException(
                                "Una asignación no tiene un empleado asociado."
                        );
                    }

                    return empleado.liquidarHaberes(
                            festival,
                            costos
                    );
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}