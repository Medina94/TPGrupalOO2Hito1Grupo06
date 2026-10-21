package com.grupo06.epicentrogourmet.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SueldoEmpleadoDTO {

    private Long idAsignacion;
    private String festival;
    private String temporada;
    private LocalDate fechaInicioFestival;
    private LocalDate fechaFinFestival;
    private String unidadVenta;
    private BigDecimal sueldoLiquidado;

    public SueldoEmpleadoDTO(
            Long idAsignacion,
            String festival,
            String temporada,
            LocalDate fechaInicioFestival,
            LocalDate fechaFinFestival,
            String unidadVenta,
            BigDecimal sueldoLiquidado) {

        this.idAsignacion = idAsignacion;
        this.festival = festival;
        this.temporada = temporada;
        this.fechaInicioFestival = fechaInicioFestival;
        this.fechaFinFestival = fechaFinFestival;
        this.unidadVenta = unidadVenta;
        this.sueldoLiquidado = sueldoLiquidado;
    }

    public Long getIdAsignacion() {
        return idAsignacion;
    }

    public String getFestival() {
        return festival;
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

    public String getUnidadVenta() {
        return unidadVenta;
    }

    public BigDecimal getSueldoLiquidado() {
        return sueldoLiquidado;
    }
}