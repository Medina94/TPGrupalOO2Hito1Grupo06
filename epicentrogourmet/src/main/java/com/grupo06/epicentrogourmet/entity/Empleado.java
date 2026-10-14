package com.grupo06.epicentrogourmet.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

@Entity
@Table(name = "empleado")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmpleado;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    @Column(unique = true, nullable = false)
    private int dni;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email no tiene un formato válido")
    @Column(unique = true, nullable = false)
    private String email;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento debe ser anterior a la fecha actual")
    private LocalDate fechaNacimiento;

    @NotNull(message = "La fecha de ingreso es obligatoria")
    private LocalDate fechaIngreso;

    private boolean activo = true;

    public Empleado() {
    }

    public Empleado(String nombre,
                    String apellido,
                    int dni,
                    String email,
                    LocalDate fechaNacimiento,
                    LocalDate fechaIngreso) {

        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaIngreso = fechaIngreso;
        this.activo = true;
    }

    @AssertTrue(message = "El empleado debe ser mayor de edad")
    public boolean isMayorDeEdadValido() {
        if (fechaNacimiento == null) {
            return true;
        }

        return fechaNacimiento.plusYears(18)
                .isBefore(LocalDate.now())
                || fechaNacimiento.plusYears(18)
                .isEqual(LocalDate.now());
    }

    public int calcularAntiguedad(LocalDate fechaReferencia) {

        if (fechaIngreso == null || fechaReferencia == null) {
            return 0;
        }

        return (int) ChronoUnit.YEARS.between(
                fechaIngreso,
                fechaReferencia
        );
    }

    public abstract BigDecimal liquidarHaberes(
            Festival festival,
            CostosFestival costos
    );

    public Long getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Empleado [idEmpleado=" + idEmpleado
                + ", nombre=" + nombre
                + ", apellido=" + apellido
                + ", dni=" + dni
                + ", email=" + email
                + ", fechaNacimiento=" + fechaNacimiento
                + ", fechaIngreso=" + fechaIngreso
                + ", activo=" + activo + "]";
    }
}