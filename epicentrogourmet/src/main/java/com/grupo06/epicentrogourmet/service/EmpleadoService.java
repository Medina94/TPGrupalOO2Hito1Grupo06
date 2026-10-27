package com.grupo06.epicentrogourmet.service;

import java.time.LocalDate;
import java.text.Normalizer;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

import com.grupo06.epicentrogourmet.dto.ModificarEmpleadoDTO;
import com.grupo06.epicentrogourmet.dto.RegistroEmpleadoDTO;
import com.grupo06.epicentrogourmet.entity.*;
import com.grupo06.epicentrogourmet.repository.AsignacionEmpleadoRepository;
import com.grupo06.epicentrogourmet.repository.EmpleadoRepository;
import com.grupo06.epicentrogourmet.repository.UsuarioRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmpleadoService {
    private static final Logger logger = LoggerFactory.getLogger(EmpleadoService.class);
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    private final EmpleadoRepository empleadoRepository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final AsignacionEmpleadoRepository asignacionEmpleadoRepository;
    private final UnidadVentaService unidadVentaService;
    private final IUsuarioActualService usuarioActualService;
    private final IMailService mailService;

    public EmpleadoService(
            EmpleadoRepository empleadoRepository,
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder,
            AsignacionEmpleadoRepository asignacionEmpleadoRepository,
            UnidadVentaService unidadVentaService,
            IUsuarioActualService usuarioActualService,
            IMailService mailService) {

        this.empleadoRepository = empleadoRepository;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.asignacionEmpleadoRepository = asignacionEmpleadoRepository;
        this.unidadVentaService = unidadVentaService;
        this.usuarioActualService = usuarioActualService;
        this.mailService = mailService;
    }

    // =====================================================
    // ALTA DE EMPLEADO
    // =====================================================

    @Transactional
    public Empleado registrarEmpleado(RegistroEmpleadoDTO dto) {

        dto.setEmail(dto.getEmail().trim());
        validarDatosUnicos(dto);

        // 1. Obtener la Unidad del Responsable logueado
        UnidadVenta unidadVenta =
                obtenerUnidadDelResponsableActual();

        Festival festival =
                unidadVenta.getFestival();

        if (festival == null) {
            throw new IllegalArgumentException(
                    "La Unidad de Venta no tiene un Festival asociado."
            );
        }

        // 2. Crear y guardar el empleado
        Empleado empleado =
                empleadoRepository.save(
                        crearEmpleado(dto)
                );

        // 3. Crear el usuario de acceso
        String email = dto.getEmail();
        String password = generarContrasena();
        Usuario usuario =
                new Usuario(
                        email,
                        passwordEncoder.encode(password),
                        RolUsuario.EMPLEADO,
                        empleado
                );

        usuarioRepository.save(usuario);

        // 4. Asignar el empleado a la Unidad
        AsignacionEmpleado asignacion =
                new AsignacionEmpleado(
                        empleado,
                        unidadVenta,
                        obtenerFechaInicioAsignacion(
                                festival
                        )
                );

        asignacionEmpleadoRepository.save(
                asignacion
        );

        // 5. Enviar correo de alta al empleado
        logger.info("Enviando correo de alta al empleado: {}", empleado.getEmail());
        mailService.enviarMailAlta(dto, usuario.getUsername(), password);
        return empleado;
    }

    // =====================================================
    // VALIDACIONES DE ALTA
    // =====================================================

    private void validarDatosUnicos(
            RegistroEmpleadoDTO dto) {

        String email =
                dto.getEmail().trim();

        if (empleadoRepository.existsByDni(
                dto.getDni())) {

            throw new IllegalArgumentException(
                    "Ya existe un empleado con ese DNI."
            );
        }

        if (empleadoRepository.existsByEmail(email)) {

            throw new IllegalArgumentException(
                    "Ya existe un empleado con ese email."
            );
        }

        if (usuarioRepository.existsByUsername(email)) {

            throw new IllegalArgumentException(
                    "Ya existe un usuario registrado con ese email."
            );
        }
    }

    private String generarContrasena() {
        byte[] bytes = new byte[18];
        SECURE_RANDOM.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }

    // =====================================================
    // CREACIÓN DEL EMPLEADO
    // =====================================================

    private Empleado crearEmpleado(
            RegistroEmpleadoDTO dto) {

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
                    dto.getNombre().trim(),
                    dto.getApellido().trim(),
                    dto.getDni(),
                    dto.getEmail().trim(),
                    dto.getFechaNacimiento(),
                    dto.getFechaIngreso(),
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
                        "El turno del cajero seleccionado no es válido."
                );
            }

            return new Cajero(
                    dto.getNombre().trim(),
                    dto.getApellido().trim(),
                    dto.getDni(),
                    dto.getEmail().trim(),
                    dto.getFechaNacimiento(),
                    dto.getFechaIngreso(),
                    turno
            );
        }

        throw new IllegalArgumentException(
                "El tipo de empleado seleccionado no es válido."
        );
    }

    // =====================================================
    // LISTADO DE EMPLEADOS DEL RESPONSABLE
    // =====================================================

    @Transactional(readOnly = true)
    public List<Empleado> listarEmpleados() {

        UnidadVenta unidadVenta =
                obtenerUnidadDelResponsableActual();

        return asignacionEmpleadoRepository
                .findByUnidadVenta(unidadVenta)
                .stream()
                .map(
                        AsignacionEmpleado::getEmpleado
                )
                .filter(
                        empleado ->
                                empleado != null
                )
                .filter(
                        empleado ->
                                !esResponsableDeUnidad(
                                        empleado,
                                        unidadVenta
                                )
                )
                .sorted(
                        (e1, e2) ->
                                Integer.compare(
                                        e1.getDni(),
                                        e2.getDni()
                                )
                )
                .toList();
    }

    // =====================================================
    // CONSULTA / FILTRO DE EMPLEADOS
    // =====================================================

    @Transactional(readOnly = true)
    public List<Empleado> buscarEmpleados(
            String nombre,
            String dni,
            LocalDate fechaDesde,
            LocalDate fechaHasta) {

        String nombreNormalizado = normalizar(nombre);
        String dniFiltro = dni == null ? "" : dni.trim();

        return listarEmpleados()
                .stream()
                .filter(e -> nombreNormalizado.isEmpty()
                        || normalizar(e.getNombre() + " " + e.getApellido())
                        .contains(nombreNormalizado))
                .filter(e -> dniFiltro.isEmpty()
                        || String.valueOf(e.getDni()).contains(dniFiltro))
                .filter(e -> fechaDesde == null
                        || (e.getFechaIngreso() != null
                        && !e.getFechaIngreso().isBefore(fechaDesde)))
                .filter(e -> fechaHasta == null
                        || (e.getFechaIngreso() != null
                        && !e.getFechaIngreso().isAfter(fechaHasta)))
                .toList();
    }

    private String normalizar(String texto) {

        if (texto == null) {
            return "";
        }

        return Normalizer
                .normalize(texto.trim(), Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .toLowerCase();
    }

    // =====================================================
    // BAJA LÓGICA
    // =====================================================

    @Transactional
    public void eliminarEmpleado(
            Long idEmpleado) {

        Empleado empleado =
                obtenerEmpleadoGestionable(
                        idEmpleado
                );

        empleado.setActivo(false);

        Usuario usuario =
                buscarUsuarioDelEmpleado(
                        idEmpleado
                );

        usuario.setActivo(false);
    }

    // =====================================================
    // REACTIVACIÓN
    // =====================================================

    @Transactional
    public void reactivarEmpleado(
            Long idEmpleado) {

        Empleado empleado =
                obtenerEmpleadoGestionable(
                        idEmpleado
                );

        empleado.setActivo(true);

        Usuario usuario =
                buscarUsuarioDelEmpleado(
                        idEmpleado
                );

        usuario.setActivo(true);

        logger.info("Enviando correo de reactivación al empleado: {}", empleado.getEmail());
        mailService.enviarMailReactivacion(empleado.getEmail());
    }

    // =====================================================
    // BUSCAR EMPLEADO
    // =====================================================

    @Transactional(readOnly = true)
    public Empleado buscarEmpleadoPorId(
            Long idEmpleado) {

        return obtenerEmpleadoGestionable(
                idEmpleado
        );
    }

    // =====================================================
    // MODIFICAR EMPLEADO
    // =====================================================

    @Transactional
    public Empleado modificarEmpleado(
            Long idEmpleado,
            ModificarEmpleadoDTO dto) {

        Empleado empleado =
                obtenerEmpleadoGestionable(
                        idEmpleado
                );

        if (empleadoRepository.existsByDni(
                dto.getDni())
                && empleado.getDni()
                != dto.getDni()) {

            throw new IllegalArgumentException(
                    "Ya existe otro empleado con ese DNI."
            );
        }

        String email =
                dto.getEmail().trim();

        if (empleadoRepository.existsByEmail(email)
                && !empleado
                .getEmail()
                .equalsIgnoreCase(email)) {

            throw new IllegalArgumentException(
                    "Ya existe otro empleado con ese email."
            );
        }

        empleado.setNombre(
                dto.getNombre().trim()
        );

        empleado.setApellido(
                dto.getApellido().trim()
        );

        empleado.setDni(
                dto.getDni()
        );

        empleado.setEmail(
                email
        );

        empleado.setFechaNacimiento(
                dto.getFechaNacimiento()
        );

        empleado.setFechaIngreso(
                dto.getFechaIngreso()
        );

        if (empleado instanceof Cocinero cocinero) {

            if (dto.getRolCocina() == null
                    || dto.getRolCocina().isBlank()) {

                throw new IllegalArgumentException(
                        "Debe seleccionar el rol de cocina."
                );
            }

            try {

                cocinero.setRolCocina(
                        RolCocina.valueOf(
                                dto.getRolCocina()
                                        .trim()
                                        .toUpperCase()
                        )
                );

            } catch (IllegalArgumentException e) {

                throw new IllegalArgumentException(
                        "El rol de cocina seleccionado no es válido."
                );
            }

        } else if (empleado instanceof Cajero cajero) {

            if (dto.getTurno() == null
                    || dto.getTurno().isBlank()) {

                throw new IllegalArgumentException(
                        "Debe seleccionar el turno del cajero."
                );
            }

            try {

                cajero.setTurno(
                        Turno.valueOf(
                                dto.getTurno()
                                        .trim()
                                        .toUpperCase()
                        )
                );

            } catch (IllegalArgumentException e) {

                throw new IllegalArgumentException(
                        "El turno del cajero seleccionado no es válido."
                );
            }
        }

        return empleadoRepository.save(
                empleado
        );
    }

    // =====================================================
    // UNIDAD DEL RESPONSABLE ACTUAL
    // =====================================================

    private UnidadVenta obtenerUnidadDelResponsableActual() {

        Usuario usuario =
                usuarioActualService
                        .obtenerUsuarioActual();

        if (usuario.getEmpleado() == null) {

            throw new IllegalArgumentException(
                    "El usuario Responsable no tiene un empleado asociado."
            );
        }

        UnidadVenta unidadVenta =
                unidadVentaService
                        .buscarUnidadDelResponsable(
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
    // CONTROL DE ACCESO A EMPLEADOS
    // =====================================================

    private Empleado obtenerEmpleadoGestionable(
            Long idEmpleado) {

        Empleado empleado =
                empleadoRepository
                        .findById(idEmpleado)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "No se encontró el empleado."
                                )
                        );

        UnidadVenta unidadVenta =
                obtenerUnidadDelResponsableActual();

        if (esResponsableDeUnidad(
                empleado,
                unidadVenta)) {

            throw new IllegalArgumentException(
                    "El Responsable no puede modificarse, darse de baja o reactivarse desde la gestión de empleados."
            );
        }

        boolean perteneceAUnidad =
                asignacionEmpleadoRepository
                        .findByEmpleado(empleado)
                        .stream()
                        .anyMatch(
                                asignacion ->
                                        asignacion
                                                .getUnidadVenta()
                                                .getIdUnidadVenta()
                                                .equals(
                                                        unidadVenta
                                                                .getIdUnidadVenta()
                                                )
                        );

        if (!perteneceAUnidad) {

            throw new IllegalArgumentException(
                    "El empleado no pertenece a la Unidad de Venta del Responsable."
            );
        }

        return empleado;
    }

    // =====================================================
    // USUARIO ASOCIADO AL EMPLEADO
    // =====================================================

    private Usuario buscarUsuarioDelEmpleado(
            Long idEmpleado) {

        return usuarioRepository
                .findAll()
                .stream()
                .filter(
                        usuario ->
                                usuario.getEmpleado()
                                        != null
                                        && usuario
                                        .getEmpleado()
                                        .getIdEmpleado()
                                        .equals(idEmpleado)
                )
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "No se encontró el usuario asociado al empleado."
                        )
                );
    }

    // =====================================================
    // RESPONSABLE DE LA UNIDAD
    // =====================================================

    private boolean esResponsableDeUnidad(
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

    // =====================================================
    // FECHA DE INICIO DE ASIGNACIÓN
    // =====================================================

    private LocalDate obtenerFechaInicioAsignacion(
            Festival festival) {

        LocalDate hoy =
                LocalDate.now();

        if (festival
                .getFechaInicio()
                .isAfter(hoy)) {

            return festival
                    .getFechaInicio();
        }

        return hoy;
    }
}