package com.grupo06.epicentrogourmet.dto;

import java.math.BigDecimal;

public class RentabilidadDTO {

    private String nombreUnidad;

    private BigDecimal ingresosPedidos =
            BigDecimal.ZERO;

    private BigDecimal costosPlatos =
            BigDecimal.ZERO;

    private BigDecimal costosSueldos =
            BigDecimal.ZERO;

    private BigDecimal costoCanon =
            BigDecimal.ZERO;

    private BigDecimal rentabilidadNeta =
            BigDecimal.ZERO;


    public RentabilidadDTO() {
    }


    public String getNombreUnidad() {
        return nombreUnidad;
    }

    public void setNombreUnidad(String nombreUnidad) {
        this.nombreUnidad = nombreUnidad;
    }

    public BigDecimal getIngresosPedidos() {
        return ingresosPedidos;
    }

    public void setIngresosPedidos(
            BigDecimal ingresosPedidos) {

        this.ingresosPedidos = ingresosPedidos;
    }

    public BigDecimal getCostosPlatos() {
        return costosPlatos;
    }

    public void setCostosPlatos(
            BigDecimal costosPlatos) {

        this.costosPlatos = costosPlatos;
    }

    public BigDecimal getCostosSueldos() {
        return costosSueldos;
    }

    public void setCostosSueldos(
            BigDecimal costosSueldos) {

        this.costosSueldos = costosSueldos;
    }

    public BigDecimal getCostoCanon() {
        return costoCanon;
    }

    public void setCostoCanon(
            BigDecimal costoCanon) {

        this.costoCanon = costoCanon;
    }

    public BigDecimal getRentabilidadNeta() {
        return rentabilidadNeta;
    }

    public void setRentabilidadNeta(
            BigDecimal rentabilidadNeta) {

        this.rentabilidadNeta =
                rentabilidadNeta;
    }
}