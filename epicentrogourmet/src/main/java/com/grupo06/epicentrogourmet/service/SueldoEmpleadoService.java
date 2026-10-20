package com.grupo06.epicentrogourmet.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grupo06.epicentrogourmet.dto.SueldoEmpleadoDTO;
import com.grupo06.epicentrogourmet.entity.AsignacionEmpleado;
import com.grupo06.epicentrogourmet.entity.Empleado;
import com.grupo06.epicentrogourmet.entity.Festival;
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