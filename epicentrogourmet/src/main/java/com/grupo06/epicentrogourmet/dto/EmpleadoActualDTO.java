package com.grupo06.epicentrogourmet.dto;

import java.time.LocalDate;

public class EmpleadoActualDTO {

    private String nombreEmpleado;
    private String email;
    private String tipoEmpleado;
    private String detallePuesto;
    private LocalDate fechaIngreso;

    private String nombreUnidad;
    private String codigoUnidad;
    private String tipoUnidad;
    private double superficie;
    private String responsable;

    private String nombreFestival;
    private String temporada;
    private LocalDate fechaInicioFestival;
    private LocalDate fechaFinFestival;

    public EmpleadoActualDTO(
            String nombreEmpleado,
            String email,
            String tipoEmpleado,
            String detallePuesto,
            LocalDate fechaIngreso,
            String nombreUnidad,
            String codigoUnidad,
            String tipoUnidad,
            double superficie,
            String responsable,
            String nombreFestival,
            String temporada,
            LocalDate fechaInicioFestival,
            LocalDate fechaFinFestival) {

        this.nombreEmpleado = nombreEmpleado;
        this.email = email;
        this.tipoEmpleado = tipoEmpleado;
        this.detallePuesto = detallePuesto;
        this.fechaIngreso = fechaIngreso;
        this.nombreUnidad = nombreUnidad;
        this.codigoUnidad = codigoUnidad;
        this.tipoUnidad = tipoUnidad;
        this.superficie = superficie;
        this.responsable = responsable;
        this.nombreFestival = nombreFestival;
        this.temporada = temporada;
        this.fechaInicioFestival = fechaInicioFestival;
        this.fechaFinFestival = fechaFinFestival;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public String getEmail() {
        return email;
    }

    public String getTipoEmpleado() {
        return tipoEmpleado;
    }

    public String getDetallePuesto() {
        return detallePuesto;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public String getNombreUnidad() {
        return nombreUnidad;
    }

    public String getCodigoUnidad() {
        return codigoUnidad;
    }

    public String getTipoUnidad() {
        return tipoUnidad;
    }

    public double getSuperficie() {
        return superficie;
    }

    public String getResponsable() {
        return responsable;
    }

    public String getNombreFestival() {
        return nombreFestival;
    }

    public String getTemporada() {
        return temporada;
    }

    public LocalDate getFechaInicioFestival() {
        return fechaInicioFestival;
    }

    public LocalDate getFechaFinFestival() {
        return fechaFinFestival;
    }
}