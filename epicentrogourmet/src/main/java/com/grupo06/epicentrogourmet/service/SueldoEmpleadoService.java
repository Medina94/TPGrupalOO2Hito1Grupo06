package com.grupo06.epicentrogourmet.service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grupo06.epicentrogourmet.dto.ReciboSueldoDTO;
import com.grupo06.epicentrogourmet.dto.SueldoEmpleadoDTO;
import com.grupo06.epicentrogourmet.entity.AsignacionEmpleado;
import com.grupo06.epicentrogourmet.entity.Cajero;
import com.grupo06.epicentrogourmet.entity.Cocinero;
import com.grupo06.epicentrogourmet.entity.CostosFestival;
import com.grupo06.epicentrogourmet.entity.Empleado;
import com.grupo06.epicentrogourmet.entity.Festival;
import com.grupo06.epicentrogourmet.entity.RolCocina;
import com.grupo06.epicentrogourmet.entity.Usuario;
import com.grupo06.epicentrogourmet.repository.AsignacionEmpleadoRepository;

@Service
public class SueldoEmpleadoService {

    private final AsignacionEmpleadoRepository asignacionEmpleadoRepository;
    private final IUsuarioActualService usuarioActualService;

    public SueldoEmpleadoService(
            AsignacionEmpleadoRepository asignacionEmpleadoRepository,
            IUsuarioActualService usuarioActualService) {

        this.asignacionEmpleadoRepository =
                asignacionEmpleadoRepository;

        this.usuarioActualService =
                usuarioActualService;
    }

    @Transactional(readOnly = true)
    public Empleado obtenerEmpleadoActual() {

        Usuario usuario =
                usuarioActualService.obtenerUsuarioActual();

        if (usuario.getEmpleado() == null) {

            throw new IllegalArgumentException(
                    "El usuario autenticado no tiene un empleado asociado."
            );
        }

        return usuario.getEmpleado();
    }

    @Transactional(readOnly = true)
    public List<SueldoEmpleadoDTO> listarSueldosPorFestival() {

        Empleado empleado =
                obtenerEmpleadoActual();

        return asignacionEmpleadoRepository
                .findByEmpleado(empleado)
                .stream()
                .filter(asignacion ->
                        asignacion.getUnidadVenta() != null
                )
                .filter(asignacion ->
                        asignacion
                                .getUnidadVenta()
                                .getFestival() != null
                )
                .sorted(
                        Comparator.comparing(
                                (AsignacionEmpleado asignacion) ->
                                        asignacion
                                                .getUnidadVenta()
                                                .getFestival()
                                                .getFechaInicio()
                        ).reversed()
                )
                .map(this::crearDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public ReciboSueldoDTO obtenerReciboSueldo(
            Long idAsignacion) {

        // Obtener el empleado actual
        Empleado empleadoActual = obtenerEmpleadoActual();

        // Obtener la asignación de empleado por su ID
        AsignacionEmpleado asignacion =
                asignacionEmpleadoRepository.findById(idAsignacion)
                        .orElseThrow(() -> new IllegalArgumentException(
                                "No se encontró el recibo de sueldo solicitado."
                        ));

        if (!asignacion.getEmpleado().getIdEmpleado()
                .equals(empleadoActual.getIdEmpleado())) {
            throw new IllegalArgumentException(
                    "No tenés permiso para descargar este recibo de sueldo."
            );
        }

        if (asignacion.getSueldoLiquidado() == null) {
            throw new IllegalStateException(
                    "El sueldo de esta asignación todavía no fue liquidado."
            );
        }

        Festival festival = asignacion.getUnidadVenta().getFestival();
        CostosFestival costos = festival.getCostos();
        if (costos == null) {
            throw new IllegalStateException(
                    "El festival no tiene costos configurados para generar el recibo."
            );
        }

        Empleado empleado =
                (Empleado) Hibernate.unproxy(asignacion.getEmpleado());

        // Calculos d eantiguedad, plus por categoria y plus por antiguedad
        int antiguedad =
                empleado.calcularAntiguedad(festival.getFechaInicio());
        long diasFestival = java.time.temporal.ChronoUnit.DAYS.between(
                festival.getFechaInicio(),
                festival.getFechaFin()
        ) + 1;
        int cantidadTramos = (int) Math.ceil(diasFestival / 15.0);
        BigDecimal factorLiquidacion = BigDecimal.valueOf(cantidadTramos)
                .multiply(new BigDecimal("0.5"));
        BigDecimal sueldoBase =
                costos.getSueldoBase().multiply(factorLiquidacion);
        BigDecimal plusCategoria = BigDecimal.ZERO;
        BigDecimal plusAntiguedad = BigDecimal.ZERO;
        String tipoEmpleado;
        String categoria = "-";

        // Realizo casteo para determinar el tipo de empleado y calcular el plus correspondientes
        if (empleado instanceof Cocinero cocinero) {
            tipoEmpleado = "Cocinero";
            RolCocina rol = cocinero.getRolCocina();
            categoria = rol.name();
            BigDecimal plusPorRol = switch (rol) {
                case COCINERO -> costos.getPlusCocinero();
                case AYUDANTE -> costos.getPlusAyudante();
                case LAVAPLATOS -> costos.getPlusLavaplatos();
            };
            plusCategoria = plusPorRol.multiply(factorLiquidacion);
        } else if (empleado instanceof Cajero cajero) {
            tipoEmpleado = "Cajero";
            categoria = "Turno "
                    + cajero.getTurno().name().toLowerCase(Locale.ROOT);
            plusAntiguedad = costos.getValorPorAnioAntiguedad()
                    .multiply(BigDecimal.valueOf(antiguedad))
                    .multiply(factorLiquidacion);
        } else {
            tipoEmpleado = "Empleado";
            sueldoBase = BigDecimal.ZERO;
        }

        // Retorno DTO para con los datos necesarios para generar el recibo de sueldo
        return new ReciboSueldoDTO(
                empleado.getNombre() + " " + empleado.getApellido(),
                tipoEmpleado,
                asignacion.getUnidadVenta().getNombreComercial(),
                festival.getNombre(),
                festival.getTemporada(),
                asignacion.getFechaDesde(),
                asignacion.getFechaHasta(),
                sueldoBase,
                plusCategoria,
                categoria,
                plusAntiguedad,
                antiguedad,
                asignacion.getSueldoLiquidado()
        );
    }

    private SueldoEmpleadoDTO crearDTO(
            AsignacionEmpleado asignacion) {

        Festival festival =
                asignacion
                        .getUnidadVenta()
                        .getFestival();

        return new SueldoEmpleadoDTO(
                asignacion.getIdAsignacion(),
                festival.getNombre(),
                festival.getTemporada(),
                festival.getFechaInicio(),
                festival.getFechaFin(),
                asignacion
                        .getUnidadVenta()
                        .getNombreComercial(),
                asignacion.getSueldoLiquidado()
        );
    }
}