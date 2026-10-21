package com.grupo06.epicentrogourmet.dto;

import java.time.LocalDate;

public class HistorialLaboralDTO {

    private Long idAsignacion;
    private String festival;
    private String temporada;
    private String unidadVenta;
    private String codigoUnidad;
    private String tipoUnidad;
    private LocalDate fechaDesde;
    private LocalDate fechaHasta;
    private String estado;

    public HistorialLaboralDTO(
            Long idAsignacion,
            String festival,
            String temporada,
            String unidadVenta,
            String codigoUnidad,
            String tipoUnidad,
            LocalDate fechaDesde,
            LocalDate fechaHasta,
            String estado) {

        this.idAsignacion = idAsignacion;
        this.festival = festival;
        this.temporada = temporada;
        this.unidadVenta = unidadVenta;
        this.codigoUnidad = codigoUnidad;
        this.tipoUnidad = tipoUnidad;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.estado = estado;
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

    public String getUnidadVenta() {
        return unidadVenta;
    }

    public String getCodigoUnidad() {
        return codigoUnidad;
    }

    public String getTipoUnidad() {
        return tipoUnidad;
    }

    public LocalDate getFechaDesde() {
        return fechaDesde;
    }

    public LocalDate getFechaHasta() {
        return fechaHasta;
    }

    public String getEstado() {
        return estado;
    }
}