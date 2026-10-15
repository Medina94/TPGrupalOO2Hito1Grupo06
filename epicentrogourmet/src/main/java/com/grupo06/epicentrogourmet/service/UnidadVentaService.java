package com.grupo06.epicentrogourmet.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grupo06.epicentrogourmet.dto.RegistroUnidadResponsableDTO;
import com.grupo06.epicentrogourmet.entity.AsignacionEmpleado;
import com.grupo06.epicentrogourmet.entity.Cajero;
import com.grupo06.epicentrogourmet.entity.Cocinero;
import com.grupo06.epicentrogourmet.entity.Empleado;
import com.grupo06.epicentrogourmet.entity.Festival;
import com.grupo06.epicentrogourmet.entity.FoodTruck;
import com.grupo06.epicentrogourmet.entity.PuestoDesarmable;
import com.grupo06.epicentrogourmet.entity.RolCocina;
import com.grupo06.epicentrogourmet.entity.RolUsuario;
import com.grupo06.epicentrogourmet.entity.Turno;
import com.grupo06.epicentrogourmet.entity.UnidadVenta;
import com.grupo06.epicentrogourmet.entity.Usuario;
import com.grupo06.epicentrogourmet.repository.AsignacionEmpleadoRepository;
import com.grupo06.epicentrogourmet.repository.EmpleadoRepository;
import com.grupo06.epicentrogourmet.repository.FestivalRepository;
import com.grupo06.epicentrogourmet.repository.UnidadVentaRepository;
import com.grupo06.epicentrogourmet.repository.UsuarioRepository;

@Service
public class UnidadVentaService {

    private final UnidadVentaRepository unidadVentaRepository;
    private final EmpleadoRepository empleadoRepository;
    private final FestivalRepository festivalRepository;
    private final UsuarioRepository usuarioRepository;
    private final AsignacionEmpleadoRepository asignacionEmpleadoRepository;
    private final PasswordEncoder passwordEncoder;

    public UnidadVentaService(
            UnidadVentaRepository unidadVentaRepository,
            EmpleadoRepository empleadoRepository,
            FestivalRepository festivalRepository,
            UsuarioRepository usuarioRepository,
            AsignacionEmpleadoRepository asignacionEmpleadoRepository,
            PasswordEncoder passwordEncoder) {

        this.unidadVentaRepository = unidadVentaRepository;
        this.empleadoRepository = empleadoRepository;
        this.festivalRepository = festivalRepository;
        this.usuarioRepository = usuarioRepository;
        this.asignacionEmpleadoRepository = asignacionEmpleadoRepository;
        this.passwordEncoder = passwordEncoder;
    }


    // CONSULTA DE UNIDADES POR FESTIVAL

    @Transactional(readOnly = true)
    public List<UnidadVenta> listarPorFestival(Long idFestival) {

        return unidadVentaRepository
                .findByFestival_IdFestivalOrderByNombreComercialAsc(
                        idFestival
                );
    }


    // REGISTRO DE UNIDAD + RESPONSABLE

    @Transactional
    public UnidadVenta registrarUnidadConResponsable(
            RegistroUnidadResponsableDTO dto) {

        validarDatosUnicos(dto);

        Festival festival = festivalRepository
                .findById(dto.getIdFestival())
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "El festival seleccionado no existe."
                        )
                );

        Empleado responsable = crearResponsable(dto);

        responsable =
                empleadoRepository.save(responsable);

        Usuario usuario = new Usuario(
                dto.getUsername().trim(),
                passwordEncoder.encode(dto.getPassword()),
                RolUsuario.RESPONSABLE,
                responsable
        );

        usuarioRepository.save(usuario);

        UnidadVenta unidadVenta =
                crearUnidadVenta(
                        dto,
                        festival,
                        responsable
                );

        unidadVenta =
                unidadVentaRepository.save(unidadVenta);

        AsignacionEmpleado asignacion =
                new AsignacionEmpleado(
                        responsable,
                        unidadVenta,
                        obtenerFechaInicioAsignacion(festival)
                );

        asignacionEmpleadoRepository.save(asignacion);

        return unidadVenta;
    }


    // VALIDACIONES DE DATOS ÚNICOS

    private void validarDatosUnicos(
            RegistroUnidadResponsableDTO dto) {

        String codigo =
                dto.getCodigo().trim();

        String email =
                dto.getEmailResponsable().trim();

        String username =
                dto.getUsername().trim();

        if (unidadVentaRepository.existsByCodigo(codigo)) {

            throw new IllegalArgumentException(
                    "Ya existe una unidad de venta con ese código."
            );
        }

        if (empleadoRepository.existsByDni(
                dto.getDniResponsable())) {

            throw new IllegalArgumentException(
                    "Ya existe un empleado con ese DNI."
            );
        }

        if (empleadoRepository.existsByEmail(email)) {

            throw new IllegalArgumentException(
                    "Ya existe un empleado con ese email."
            );
        }

        if (usuarioRepository.existsByUsername(username)) {

            throw new IllegalArgumentException(
                    "El nombre de usuario ya se encuentra registrado."
            );
        }
    }


    // CREACIÓN DEL RESPONSABLE

    private Empleado crearResponsable(
            RegistroUnidadResponsableDTO dto) {

        String tipoEmpleado =
                dto.getTipoEmpleado()
                        .trim()
                        .toUpperCase();

        if ("COCINERO".equals(tipoEmpleado)) {

            if (dto.getRolCocina() == null
                    || dto.getRolCocina().isBlank()) {

                throw new IllegalArgumentException(
                        "Debe seleccionar el rol de cocina."
                );
            }

            RolCocina rolCocina;

            try {

                rolCocina =
                        RolCocina.valueOf(
                                dto.getRolCocina()
                                        .trim()
                                        .toUpperCase()
                        );

            } catch (IllegalArgumentException e) {

                throw new IllegalArgumentException(
                        "El rol de cocina seleccionado no es válido."
                );
            }

            return new Cocinero(
                    dto.getNombreResponsable().trim(),
                    dto.getApellidoResponsable().trim(),
                    dto.getDniResponsable(),
                    dto.getEmailResponsable().trim(),
                    dto.getFechaNacimientoResponsable(),
                    dto.getFechaIngresoResponsable(),
                    rolCocina
            );
        }


        if ("CAJERO".equals(tipoEmpleado)) {

            if (dto.getTurno() == null
                    || dto.getTurno().isBlank()) {

                throw new IllegalArgumentException(
                        "Debe seleccionar el turno del cajero."
                );
            }

            Turno turno;

            try {

                turno =
                        Turno.valueOf(
                                dto.getTurno()
                                        .trim()
                                        .toUpperCase()
                        );

            } catch (IllegalArgumentException e) {

                throw new IllegalArgumentException(
                        "El turno seleccionado no es válido."
                );
            }

            return new Cajero(
                    dto.getNombreResponsable().trim(),
                    dto.getApellidoResponsable().trim(),
                    dto.getDniResponsable(),
                    dto.getEmailResponsable().trim(),
                    dto.getFechaNacimientoResponsable(),
                    dto.getFechaIngresoResponsable(),
                    turno
            );
        }


        throw new IllegalArgumentException(
                "El tipo de empleado seleccionado no es válido."
        );
    }


    // CREACIÓN DE LA UNIDAD

    private UnidadVenta crearUnidadVenta(
            RegistroUnidadResponsableDTO dto,
            Festival festival,
            Empleado responsable) {

        String tipoUnidad =
                dto.getTipoUnidad()
                        .trim()
                        .toUpperCase();

        if ("FOOD_TRUCK".equals(tipoUnidad)) {

            if (dto.getPatente() == null
                    || dto.getPatente().isBlank()) {

                throw new IllegalArgumentException(
                        "La patente del Food Truck es obligatoria."
                );
            }

            return new FoodTruck(
                    dto.getNombreComercial().trim(),
                    responsable,
                    dto.getSuperficie(),
                    dto.getCodigo().trim(),
                    festival,
                    dto.getPatente().trim(),
                    dto.isConexionElectrica()
            );
        }


        if ("PUESTO_DESARMABLE".equals(tipoUnidad)) {

            if (dto.getCantidadCarpas() == null
                    || dto.getCantidadCarpas() <= 0) {

                throw new IllegalArgumentException(
                        "La cantidad de carpas debe ser mayor a cero."
                );
            }

            if (dto.getMinutosMontaje() == null
                    || dto.getMinutosMontaje() <= 0) {

                throw new IllegalArgumentException(
                        "Los minutos de montaje deben ser mayores a cero."
                );
            }

            return new PuestoDesarmable(
                    dto.getNombreComercial().trim(),
                    responsable,
                    dto.getSuperficie(),
                    dto.getCodigo().trim(),
                    festival,
                    dto.getCantidadCarpas(),
                    dto.getMinutosMontaje()
            );
        }


        throw new IllegalArgumentException(
                "El tipo de unidad seleccionado no es válido."
        );
    }


    // FECHA DE INICIO DE LA ASIGNACIÓN

    private LocalDate obtenerFechaInicioAsignacion(
            Festival festival) {

        LocalDate hoy =
                LocalDate.now();

        if (festival.getFechaInicio().isAfter(hoy)) {
            return festival.getFechaInicio();
        }

        return hoy;
    }
}