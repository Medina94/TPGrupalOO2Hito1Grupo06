package com.grupo06.epicentrogourmet.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LiquidacionSueldoDTO {

    private Long idEmpleado;
    private String nombre;
    private String apellido;
    private String email;
    private String tipoEmpleado;
    private String categoriaTurno;
    private LocalDate fechaIngreso;
    private boolean activo;
    private BigDecimal sueldoLiquidado;

    public LiquidacionSueldoDTO(
            Long idEmpleado,
            String nombre,
            String apellido,
            String email,
            String tipoEmpleado,
            String categoriaTurno,
            LocalDate fechaIngreso,
            boolean activo,
            BigDecimal sueldoLiquidado) {

        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.tipoEmpleado = tipoEmpleado;
        this.categoriaTurno = categoriaTurno;
        this.fechaIngreso = fechaIngreso;
        this.activo = activo;
        this.sueldoLiquidado = sueldoLiquidado;
    }

    public Long getIdEmpleado() {
        return idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
        return email;
    }

    public String getTipoEmpleado() {
        return tipoEmpleado;
    }

    public String getCategoriaTurno() {
        return categoriaTurno;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public boolean isActivo() {
        return activo;
    }

    public BigDecimal getSueldoLiquidado() {
        return sueldoLiquidado;
    }
}