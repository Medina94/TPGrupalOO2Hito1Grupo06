package com.grupo06.epicentrogourmet.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class RegistroEmpleadoDTO {

    // DATOS DEL EMPLEADO
    // (los nombres de los campos coinciden con los del formulario)

    @NotBlank(message = "El nombre del empleado es obligatorio")
    private String nombre;

    @NotBlank(message = "El apellido del empleado es obligatorio")
    private String apellido;

    @NotNull(message = "El DNI es obligatorio")
    @Positive(message = "El DNI debe ser mayor a cero")
    private Integer dni;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email no tiene un formato válido")
    private String email;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento debe ser anterior a hoy")
    private LocalDate fechaNacimiento;

    @NotNull(message = "La fecha de ingreso es obligatoria")
    @PastOrPresent(message = "La fecha de ingreso no puede ser futura")
    private LocalDate fechaIngreso;

    // TIPO DE EMPLEADO

    @NotBlank(message = "El tipo de empleado es obligatorio")
    private String tipoEmpleado;

    private String rolCocina;

    private String turno;

    // CREDENCIALES

    @NotBlank(message = "El nombre de usuario es obligatorio")
    private String username;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String password;

    public RegistroEmpleadoDTO() {
    }

    // VALIDACIÓN DE MAYORÍA DE EDAD

    @AssertTrue(message = "El empleado debe ser mayor de 18 años")
    public boolean isMayorDeEdad() {

        if (fechaNacimiento == null) {
            return true;
        }

        return !fechaNacimiento.plusYears(18).isAfter(LocalDate.now());
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public Integer getDni() { return dni; }
    public void setDni(Integer dni) { this.dni = dni; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public LocalDate getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(LocalDate fechaIngreso) { this.fechaIngreso = fechaIngreso; }

    public String getTipoEmpleado() { return tipoEmpleado; }
    public void setTipoEmpleado(String tipoEmpleado) { this.tipoEmpleado = tipoEmpleado; }

    public String getRolCocina() { return rolCocina; }
    public void setRolCocina(String rolCocina) { this.rolCocina = rolCocina; }

    public String getTurno() { return turno; }
    public void setTurno(String turno) { this.turno = turno; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}