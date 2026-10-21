package com.grupo06.epicentrogourmet.service;

import java.util.Comparator;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grupo06.epicentrogourmet.dto.HistorialLaboralDTO;
import com.grupo06.epicentrogourmet.entity.AsignacionEmpleado;
import com.grupo06.epicentrogourmet.entity.Empleado;
import com.grupo06.epicentrogourmet.entity.Festival;
import com.grupo06.epicentrogourmet.entity.FoodTruck;
import com.grupo06.epicentrogourmet.entity.PuestoDesarmable;
import com.grupo06.epicentrogourmet.entity.UnidadVenta;
import com.grupo06.epicentrogourmet.entity.Usuario;
import com.grupo06.epicentrogourmet.repository.AsignacionEmpleadoRepository;

@Service
public class HistorialLaboralService {

    private final AsignacionEmpleadoRepository asignacionEmpleadoRepository;
    private final IUsuarioActualService usuarioActualService;

    public HistorialLaboralService(
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
                usuarioActualService
                        .obtenerUsuarioActual();

        if (usuario.getEmpleado() == null) {

            throw new IllegalArgumentException(
                    "El usuario autenticado no tiene un empleado asociado."
            );
        }

        return usuario.getEmpleado();
    }

    @Transactional(readOnly = true)
    public List<HistorialLaboralDTO> obtenerHistorial() {

        Empleado empleado =
                obtenerEmpleadoActual();

        return asignacionEmpleadoRepository
                .findByEmpleado(empleado)
                .stream()
                .filter(asignacion ->
                        asignacion.getUnidadVenta() != null
                )
                .sorted(
                        Comparator.comparing(
                                AsignacionEmpleado::getFechaDesde,
                                Comparator.nullsLast(
                                        Comparator.reverseOrder()
                                )
                        )
                )
                .map(this::crearDTO)
                .toList();
    }

    private HistorialLaboralDTO crearDTO(
            AsignacionEmpleado asignacion) {

        UnidadVenta unidad =
                (UnidadVenta) Hibernate.unproxy(
                        asignacion.getUnidadVenta()
                );

        Festival festival =
                unidad.getFestival();

        String tipoUnidad;

        if (unidad instanceof FoodTruck) {

            tipoUnidad =
                    "Food Truck";

        } else if (unidad instanceof PuestoDesarmable) {

            tipoUnidad =
                    "Puesto Desarmable";

        } else {

            tipoUnidad =
                    "Unidad de Venta";
        }

        String nombreFestival =
                festival != null
                        ? festival.getNombre()
                        : "Sin Festival";

        String temporada =
                festival != null
                        ? festival.getTemporada()
                        : "-";

        String estado =
                asignacion.getFechaHasta() == null
                        ? "Actual"
                        : "Finalizado";

        return new HistorialLaboralDTO(
                asignacion.getIdAsignacion(),
                nombreFestival,
                temporada,
                unidad.getNombreComercial(),
                unidad.getCodigo(),
                tipoUnidad,
                asignacion.getFechaDesde(),
                asignacion.getFechaHasta(),
                estado
        );
    }
}