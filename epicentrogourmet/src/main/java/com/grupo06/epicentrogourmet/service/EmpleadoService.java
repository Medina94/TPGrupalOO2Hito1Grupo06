package com.grupo06.epicentrogourmet.service;

import java.time.LocalDate;
import java.util.List;

import com.grupo06.epicentrogourmet.dto.RegistroEmpleadoDTO;
import com.grupo06.epicentrogourmet.entity.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.grupo06.epicentrogourmet.repository.EmpleadoRepository;
import com.grupo06.epicentrogourmet.dto.ModificarEmpleadoDTO;
import com.grupo06.epicentrogourmet.repository.UsuarioRepository;

@Service
public class EmpleadoService {


    private final EmpleadoRepository empleadoRepository;

    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;

    public EmpleadoService(

            EmpleadoRepository empleadoRepository,

            UsuarioRepository usuarioRepository,

            PasswordEncoder passwordEncoder) {


        this.empleadoRepository = empleadoRepository;

        this.usuarioRepository = usuarioRepository;

        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Empleado registrarEmpleado(RegistroEmpleadoDTO dto) {

        validarDatosUnicos(dto);

        // 1. Se crea y guarda el empleado (Cocinero o Cajero)
        Empleado empleado = empleadoRepository.save(crearEmpleado(dto));

        // 2. Se crea el usuario de acceso, vinculado al empleado
        Usuario usuario = new Usuario(
                dto.getUsername().trim(),
                passwordEncoder.encode(dto.getPassword()),
                RolUsuario.EMPLEADO,
                empleado
        );

        usuarioRepository.save(usuario);

        return empleado;
    }

    private void validarDatosUnicos(
            RegistroEmpleadoDTO dto) {


        String email = dto.getEmail().trim();
        String username = dto.getUsername().trim();



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

        if (usuarioRepository.existsByUsername(username)) {
            throw new IllegalArgumentException(
                    "El nombre de usuario ya se encuentra registrado."
            );
        }
    }

    private Empleado crearEmpleado(
            RegistroEmpleadoDTO dto) {

        String tipoEmpleado =
                dto.getTipoEmpleado().trim().toUpperCase();

        if ("COCINERO".equals(tipoEmpleado)) {

            if (dto.getRolCocina() == null
                    || dto.getRolCocina().isBlank()) {

                throw new IllegalArgumentException(
                        "Debe seleccionar el rol de cocina."
                );
            }

            RolCocina rolCocina;

            try {
                rolCocina = RolCocina.valueOf(
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
                turno = Turno.valueOf(
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



    private LocalDate obtenerFechaInicioAsignacion(
            Festival festival) {

        LocalDate hoy = LocalDate.now();

        if (festival.getFechaInicio().isAfter(hoy)) {
            return festival.getFechaInicio();
        }

        return hoy;
    }

    @Transactional(readOnly = true)
    public List<Empleado> listarEmpleados() {
        return empleadoRepository.findAllByOrderByDniAsc();
    }

    @Transactional
    public void eliminarEmpleado(Long idEmpleado) {
        Empleado empleado = empleadoRepository.findById(idEmpleado)
                .orElseThrow(() -> new IllegalArgumentException("No se encontro el empleado"));
        empleado.setActivo(false);

        Usuario usuario = usuarioRepository.findAll().stream().filter(u -> u.getEmpleado() != null && u.getEmpleado().getIdEmpleado().equals(idEmpleado)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No se encontro el usuario asociado al empleado"));
        usuario.setActivo(false);
    }

    @Transactional
    public void reactivarEmpleado(Long idEmpleado) {
        Empleado empleado = empleadoRepository.findById(idEmpleado)
                .orElseThrow(() -> new IllegalArgumentException("No se encontro el empleado"));
        empleado.setActivo(true);

        Usuario usuario = usuarioRepository.findAll().stream().filter(u -> u.getEmpleado() != null && u.getEmpleado().getIdEmpleado().equals(idEmpleado)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No se encontro el usuario asociado al empleado"));
        usuario.setActivo(true);
    }

    @Transactional(readOnly = true)
    public Empleado buscarEmpleadoPorId(Long idEmpleado) {
        return empleadoRepository.findById(idEmpleado).orElseThrow(() -> new IllegalArgumentException("No se encontro el empleado"));
    }

    @Transactional
    public Empleado modificarEmpleado(Long idEmpleado, ModificarEmpleadoDTO dto) {
        Empleado empleado = empleadoRepository.findById(idEmpleado).orElseThrow(() -> new IllegalArgumentException("No se encontro el empleado"));

        if (empleadoRepository.existsByDni(dto.getDni()) && empleado.getDni() != dto.getDni()) {
            throw new IllegalArgumentException("Ya existe otro empleado con ese DNI");
        }

        String email = dto.getEmail().trim();

        if (empleadoRepository.existsByEmail(email) && !empleado.getEmail().equalsIgnoreCase(email)) {
            throw new IllegalArgumentException("Ya existe otro empleado con ese email");
        }

        empleado.setNombre(dto.getNombre().trim());
        empleado.setApellido(dto.getApellido().trim());
        empleado.setDni(dto.getDni());
        empleado.setEmail(email);
        empleado.setFechaNacimiento(dto.getFechaNacimiento());
        empleado.setFechaIngreso(dto.getFechaIngreso());

        if (empleado instanceof Cocinero cocinero) {
            if (dto.getRolCocina() == null || dto.getRolCocina().isBlank()) {
                throw new IllegalArgumentException("Debe seleccionar el rol de cocina");
            }

            cocinero.setRolCocina(RolCocina.valueOf(dto.getRolCocina().trim().toUpperCase()));

        } else if (empleado instanceof Cajero cajero) {
            if (dto.getTurno() == null || dto.getTurno().isBlank()) {
                throw new IllegalArgumentException("Debe seleccionar el turno del cajero");
            }
            cajero.setTurno(Turno.valueOf(dto.getTurno().trim().toUpperCase()));
        }

        return empleadoRepository.save(empleado);
    }

}