package com.grupo06.epicentrogourmet.dto;

import java.math.BigDecimal;

public class ReporteMayoresCanonDTO {

    private Long idUnidadVenta;
    private String nombreComercial;
    private String codigo;
    private String tipoUnidad;
    private BigDecimal canon;

    public ReporteMayoresCanonDTO(
            Long idUnidadVenta,
            String nombreComercial,
            String codigo,
            String tipoUnidad,
            BigDecimal canon) {

        this.idUnidadVenta = idUnidadVenta;
        this.nombreComercial = nombreComercial;
        this.codigo = codigo;
        this.tipoUnidad = tipoUnidad;
        this.canon = canon;
    }

    public Long getIdUnidadVenta() {
        return idUnidadVenta;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getTipoUnidad() {
        return tipoUnidad;
    }

    public BigDecimal getCanon() {
        return canon;
    }
}