package com.grupo06.epicentrogourmet.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ReciboSueldoDTO {

    private final String empleado;
    private final String tipoEmpleado;
    private final String unidadVenta;
    private final String festival;
    private final String temporada;
    private final LocalDate fechaDesde;
    private final LocalDate fechaHasta;
    private final BigDecimal sueldoBase;
    private final BigDecimal plusCategoria;
    private final String categoria;
    private final BigDecimal plusAntiguedad;
    private final int aniosAntiguedad;
    private final BigDecimal sueldoTotal;

    public ReciboSueldoDTO(
            String empleado,
            String tipoEmpleado,
            String unidadVenta,
            String festival,
            String temporada,
            LocalDate fechaDesde,
            LocalDate fechaHasta,
            BigDecimal sueldoBase,
            BigDecimal plusCategoria,
            String categoria,
            BigDecimal plusAntiguedad,
            int aniosAntiguedad,
            BigDecimal sueldoTotal) {
        this.empleado = empleado;
        this.tipoEmpleado = tipoEmpleado;
        this.unidadVenta = unidadVenta;
        this.festival = festival;
        this.temporada = temporada;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.sueldoBase = sueldoBase;
        this.plusCategoria = plusCategoria;
        this.categoria = categoria;
        this.plusAntiguedad = plusAntiguedad;
        this.aniosAntiguedad = aniosAntiguedad;
        this.sueldoTotal = sueldoTotal;
    }

    public String getEmpleado() {
        return empleado;
    }

    public String getTipoEmpleado() {
        return tipoEmpleado;
    }

    public String getUnidadVenta() {
        return unidadVenta;
    }

    public String getFestival() {
        return festival;
    }

    public String getTemporada() {
        return temporada;
    }

    public LocalDate getFechaDesde() {
        return fechaDesde;
    }

    public LocalDate getFechaHasta() {
        return fechaHasta;
    }

    public BigDecimal getSueldoBase() {
        return sueldoBase;
    }

    public BigDecimal getPlusCategoria() {
        return plusCategoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public BigDecimal getPlusAntiguedad() {
        return plusAntiguedad;
    }

    public int getAniosAntiguedad() {
        return aniosAntiguedad;
    }

    public BigDecimal getSueldoTotal() {
        return sueldoTotal;
    }
}
