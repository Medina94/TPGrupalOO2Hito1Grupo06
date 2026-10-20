package com.grupo06.epicentrogourmet.service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grupo06.epicentrogourmet.dto.LiquidacionSueldoDTO;
import com.grupo06.epicentrogourmet.entity.AsignacionEmpleado;
import com.grupo06.epicentrogourmet.entity.Cajero;
import com.grupo06.epicentrogourmet.entity.Cocinero;
import com.grupo06.epicentrogourmet.entity.CostosFestival;
import com.grupo06.epicentrogourmet.entity.Empleado;
import com.grupo06.epicentrogourmet.entity.Festival;
import com.grupo06.epicentrogourmet.entity.UnidadVenta;
import com.grupo06.epicentrogourmet.entity.Usuario;
import com.grupo06.epicentrogourmet.repository.AsignacionEmpleadoRepository;

@Service
public class LiquidacionSueldoService {

    private final AsignacionEmpleadoRepository asignacionEmpleadoRepository;
    private final UnidadVentaService unidadVentaService;
    private final IUsuarioActualService usuarioActualService;

    public LiquidacionSueldoService(
            AsignacionEmpleadoRepository asignacionEmpleadoRepository,
            UnidadVentaService unidadVentaService,
            IUsuarioActualService usuarioActualService) {

        this.asignacionEmpleadoRepository =
                asignacionEmpleadoRepository;

        this.unidadVentaService =
                unidadVentaService;

        this.usuarioActualService =
                usuarioActualService;
    }

    // =====================================================
    // UNIDAD DEL RESPONSABLE
    // =====================================================

    @Transactional(readOnly = true)
    public UnidadVenta obtenerUnidadDelResponsable() {

        Usuario usuario =
                usuarioActualService.obtenerUsuarioActual();

        UnidadVenta unidadVenta =
                unidadVentaService.buscarUnidadDelResponsable(
                        usuario.getUsername()
                );

        if (unidadVenta == null) {

            throw new IllegalArgumentException(
                    "El Responsable no tiene una Unidad de Venta asignada."
            );
        }

        return unidadVenta;
    }


    // =====================================================
    // LISTADO PARA LA PANTALLA
    // =====================================================

    @Transactional(readOnly = true)
    public List<LiquidacionSueldoDTO> listarLiquidaciones() {

        UnidadVenta unidadVenta =
                obtenerUnidadDelResponsable();

        return obtenerAsignacionesLiquidables(
                unidadVenta
        )
                .stream()
                .map(this::crearDTO)
                .toList();
    }


    // =====================================================
    // LIQUIDAR SUELDOS
    // =====================================================

    @Transactional
    public int liquidarSueldos() {

        UnidadVenta unidadVenta =
                obtenerUnidadDelResponsable();

        Festival festival =
                unidadVenta.getFestival();

        if (festival == null) {

            throw new IllegalArgumentException(
                    "La Unidad de Venta no tiene un Festival asociado."
            );
        }

        CostosFestival costos =
                festival.getCostos();

        if (costos == null) {

            throw new IllegalArgumentException(
                    "El Festival no tiene configurados los costos necesarios para liquidar sueldos."
            );
        }

        List<AsignacionEmpleado> asignaciones =
                obtenerAsignacionesLiquidables(
                        unidadVenta
                );

        for (AsignacionEmpleado asignacion : asignaciones) {

            Empleado empleado =
                    (Empleado) Hibernate.unproxy(
                            asignacion.getEmpleado()
                    );

            BigDecimal sueldo =
                    empleado.liquidarHaberes(
                            festival,
                            costos
                    );

            asignacion.setSueldoLiquidado(
                    sueldo
            );
        }

        asignacionEmpleadoRepository.saveAll(
                asignaciones
        );

        return asignaciones.size();
    }


    // =====================================================
    // ASIGNACIONES DE EMPLEADOS DE LA UNIDAD
    // =====================================================

    private List<AsignacionEmpleado>
    obtenerAsignacionesLiquidables(
            UnidadVenta unidadVenta) {

        return asignacionEmpleadoRepository
                .findByUnidadVenta(unidadVenta)
                .stream()
                .filter(asignacion ->
                        asignacion.getEmpleado() != null
                )
                .filter(asignacion ->
                        asignacion
                                .getEmpleado()
                                .isActivo()
                )
                .filter(asignacion ->
                        !esResponsable(
                                asignacion.getEmpleado(),
                                unidadVenta
                        )
                )
                .sorted(
                        Comparator
                                .comparing(
                                        (AsignacionEmpleado asignacion) ->
                                                asignacion
                                                        .getEmpleado()
                                                        .getApellido(),
                                        String.CASE_INSENSITIVE_ORDER
                                )
                                .thenComparing(
                                        asignacion ->
                                                asignacion
                                                        .getEmpleado()
                                                        .getNombre(),
                                        String.CASE_INSENSITIVE_ORDER
                                )
                )
                .toList();
    }


    // =====================================================
    // CONVERSIÓN A DTO
    // =====================================================

    private LiquidacionSueldoDTO crearDTO(
            AsignacionEmpleado asignacion) {

        Empleado empleado =
                (Empleado) Hibernate.unproxy(
                        asignacion.getEmpleado()
                );

        String tipoEmpleado;
        String categoriaTurno;

        if (empleado instanceof Cocinero cocinero) {

            tipoEmpleado =
                    "Cocinero";

            categoriaTurno =
                    cocinero
                            .getRolCocina()
                            .name();

        } else if (empleado instanceof Cajero cajero) {

            tipoEmpleado =
                    "Cajero";

            categoriaTurno =
                    cajero
                            .getTurno()
                            .name();

        } else {

            tipoEmpleado =
                    "Empleado";

            categoriaTurno =
                    "-";
        }

        return new LiquidacionSueldoDTO(
                empleado.getIdEmpleado(),
                empleado.getNombre(),
                empleado.getApellido(),
                empleado.getEmail(),
                tipoEmpleado,
                categoriaTurno,
                empleado.getFechaIngreso(),
                empleado.isActivo(),
                asignacion.getSueldoLiquidado()
        );
    }


    // =====================================================
    // EXCLUIR RESPONSABLE
    // =====================================================

    private boolean esResponsable(
            Empleado empleado,
            UnidadVenta unidadVenta) {

        if (unidadVenta.getResponsable() == null) {
            return false;
        }

        return unidadVenta
                .getResponsable()
                .getIdEmpleado()
                .equals(
                        empleado.getIdEmpleado()
                );
    }
}