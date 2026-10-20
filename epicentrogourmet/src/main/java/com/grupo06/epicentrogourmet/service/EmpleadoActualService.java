package com.grupo06.epicentrogourmet.service;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grupo06.epicentrogourmet.dto.EmpleadoActualDTO;
import com.grupo06.epicentrogourmet.entity.Cajero;
import com.grupo06.epicentrogourmet.entity.Cocinero;
import com.grupo06.epicentrogourmet.entity.Empleado;
import com.grupo06.epicentrogourmet.entity.Festival;
import com.grupo06.epicentrogourmet.entity.FoodTruck;
import com.grupo06.epicentrogourmet.entity.PuestoDesarmable;
import com.grupo06.epicentrogourmet.entity.UnidadVenta;
import com.grupo06.epicentrogourmet.entity.Usuario;

@Service
public class EmpleadoActualService {

    private final IUsuarioActualService usuarioActualService;
    private final UnidadVentaService unidadVentaService;

    public EmpleadoActualService(
            IUsuarioActualService usuarioActualService,
            UnidadVentaService unidadVentaService) {

        this.usuarioActualService =
                usuarioActualService;

        this.unidadVentaService =
                unidadVentaService;
    }

    @Transactional(readOnly = true)
    public EmpleadoActualDTO obtenerInformacionActual() {

        Usuario usuario =
                usuarioActualService
                        .obtenerUsuarioActual();

        if (usuario.getEmpleado() == null) {

            throw new IllegalArgumentException(
                    "El usuario autenticado no tiene un empleado asociado."
            );
        }

        Empleado empleado =
                (Empleado) Hibernate.unproxy(
                        usuario.getEmpleado()
                );

        UnidadVenta unidad =
                unidadVentaService
                        .buscarUnidadDelEmpleado(
                                usuario.getUsername()
                        );

        if (unidad == null) {

            throw new IllegalArgumentException(
                    "El empleado no tiene una Unidad de Venta asignada."
            );
        }

        Festival festival =
                unidad.getFestival();

        if (festival == null) {

            throw new IllegalArgumentException(
                    "La Unidad de Venta no tiene un Festival asociado."
            );
        }

        String tipoEmpleado;
        String detallePuesto;

        if (empleado instanceof Cocinero cocinero) {

            tipoEmpleado = "Cocinero";

            detallePuesto =
                    cocinero
                            .getRolCocina()
                            .name();

        } else if (empleado instanceof Cajero cajero) {

            tipoEmpleado = "Cajero";

            detallePuesto =
                    "Turno "
                            + cajero
                            .getTurno()
                            .name();

        } else {

            tipoEmpleado = "Empleado";
            detallePuesto = "-";
        }

        String tipoUnidad;

        if (unidad instanceof FoodTruck) {

            tipoUnidad = "Food Truck";

        } else if (unidad instanceof PuestoDesarmable) {

            tipoUnidad = "Puesto Desarmable";

        } else {

            tipoUnidad = "Unidad de Venta";
        }

        String responsable = "-";

        if (unidad.getResponsable() != null) {

            responsable =
                    unidad
                            .getResponsable()
                            .getNombre()
                            + " "
                            + unidad
                            .getResponsable()
                            .getApellido();
        }

        return new EmpleadoActualDTO(
                empleado.getNombre()
                        + " "
                        + empleado.getApellido(),

                empleado.getEmail(),

                tipoEmpleado,

                detallePuesto,

                empleado.getFechaIngreso(),

                unidad.getNombreComercial(),

                unidad.getCodigo(),

                tipoUnidad,

                unidad.getSuperficie(),

                responsable,

                festival.getNombre(),

                festival.getTemporada(),

                festival.getFechaInicio(),

                festival.getFechaFin()
        );
    }
}