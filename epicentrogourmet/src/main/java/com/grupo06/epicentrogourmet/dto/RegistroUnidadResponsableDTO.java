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

public class RegistroUnidadResponsableDTO {

    // DATOS DE LA UNIDAD

    @NotBlank(message = "El tipo de unidad es obligatorio")
    private String tipoUnidad;

    @NotBlank(message = "El nombre comercial es obligatorio")
    private String nombreComercial;

    @NotNull(message = "La superficie es obligatoria")
    @Positive(message = "La superficie debe ser mayor a cero")
    private Double superficie;

    @NotBlank(message = "El código es obligatorio")
    @Size(
        min = 10,
        max = 10,
        message = "El código debe tener exactamente 10 caracteres"
    )
    private String codigo;

    @NotNull(message = "El festival es obligatorio")
    private Long idFestival;


    // DATOS ESPECÍFICOS DE FOOD TRUCK

    private String patente;

    private boolean conexionElectrica;


    // DATOS ESPECÍFICOS DE PUESTO DESARMABLE

    private Integer cantidadCarpas;

    private Integer minutosMontaje;


    // DATOS DEL RESPONSABLE

    @NotBlank(message = "El nombre del responsable es obligatorio")
    private String nombreResponsable;

    @NotBlank(message = "El apellido del responsable es obligatorio")
    private String apellidoResponsable;

    @Positive(message = "El DNI debe ser mayor a cero")
    private int dniResponsable;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email no tiene un formato válido")
    private String emailResponsable;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento debe ser anterior a hoy")
    private LocalDate fechaNacimientoResponsable;

    @NotNull(message = "La fecha de ingreso es obligatoria")
    @PastOrPresent(message = "La fecha de ingreso no puede ser futura")
    private LocalDate fechaIngresoResponsable;


    // TIPO DE EMPLEADO DEL RESPONSABLE

    @NotBlank(message = "El tipo de empleado es obligatorio")
    private String tipoEmpleado;

    private String rolCocina;

    private String turno;


    // CREDENCIALES DEL RESPONSABLE

    @NotBlank(message = "El nombre de usuario es obligatorio")
    private String username;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(
        min = 6,
        message = "La contraseña debe tener al menos 6 caracteres"
    )
    private String password;


    public RegistroUnidadResponsableDTO() {
    }


    // VALIDACIÓN DE MAYORÍA DE EDAD

    @AssertTrue(message = "El responsable debe ser mayor de 18 años")
    public boolean isMayorDeEdad() {

        if (fechaNacimientoResponsable == null) {
            return true;
        }

        LocalDate fechaMayoriaEdad =
                fechaNacimientoResponsable.plusYears(18);

        return !fechaMayoriaEdad.isAfter(LocalDate.now());
    }


    public String getTipoUnidad() {
        return tipoUnidad;
    }

    public void setTipoUnidad(String tipoUnidad) {
        this.tipoUnidad = tipoUnidad;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public Double getSuperficie() {
        return superficie;
    }

    public void setSuperficie(Double superficie) {
        this.superficie = superficie;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Long getIdFestival() {
        return idFestival;
    }

    public void setIdFestival(Long idFestival) {
        this.idFestival = idFestival;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public boolean isConexionElectrica() {
        return conexionElectrica;
    }

    public void setConexionElectrica(boolean conexionElectrica) {
        this.conexionElectrica = conexionElectrica;
    }

    public Integer getCantidadCarpas() {
        return cantidadCarpas;
    }

    public void setCantidadCarpas(Integer cantidadCarpas) {
        this.cantidadCarpas = cantidadCarpas;
    }

    public Integer getMinutosMontaje() {
        return minutosMontaje;
    }

    public void setMinutosMontaje(Integer minutosMontaje) {
        this.minutosMontaje = minutosMontaje;
    }

    public String getNombreResponsable() {
        return nombreResponsable;
    }

    public void setNombreResponsable(String nombreResponsable) {
        this.nombreResponsable = nombreResponsable;
    }

    public String getApellidoResponsable() {
        return apellidoResponsable;
    }

    public void setApellidoResponsable(String apellidoResponsable) {
        this.apellidoResponsable = apellidoResponsable;
    }

    public int getDniResponsable() {
        return dniResponsable;
    }

    public void setDniResponsable(int dniResponsable) {
        this.dniResponsable = dniResponsable;
    }

    public String getEmailResponsable() {
        return emailResponsable;
    }

    public void setEmailResponsable(String emailResponsable) {
        this.emailResponsable = emailResponsable;
    }

    public LocalDate getFechaNacimientoResponsable() {
        return fechaNacimientoResponsable;
    }

    public void setFechaNacimientoResponsable(
            LocalDate fechaNacimientoResponsable) {

        this.fechaNacimientoResponsable =
                fechaNacimientoResponsable;
    }

    public LocalDate getFechaIngresoResponsable() {
        return fechaIngresoResponsable;
    }

    public void setFechaIngresoResponsable(
            LocalDate fechaIngresoResponsable) {

        this.fechaIngresoResponsable =
                fechaIngresoResponsable;
    }

    public String getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setTipoEmpleado(String tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }

    public String getRolCocina() {
        return rolCocina;
    }

    public void setRolCocina(String rolCocina) {
        this.rolCocina = rolCocina;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}