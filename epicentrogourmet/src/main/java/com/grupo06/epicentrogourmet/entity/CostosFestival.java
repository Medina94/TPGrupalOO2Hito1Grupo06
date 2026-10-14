package com.grupo06.epicentrogourmet.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "costos_festival")
public class CostosFestival {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCostos;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    private BigDecimal costoPorSuperficie;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    private BigDecimal costoPorMontaje;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    private BigDecimal plusElectricidad;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    private BigDecimal sueldoBase;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    private BigDecimal valorPorAnioAntiguedad;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    private BigDecimal plusCocinero;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    private BigDecimal plusAyudante;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    private BigDecimal plusLavaplatos;

    public CostosFestival() {
    }

    public Long getIdCostos() {
        return idCostos;
    }

    public void setIdCostos(Long idCostos) {
        this.idCostos = idCostos;
    }

    public BigDecimal getCostoPorSuperficie() {
        return costoPorSuperficie;
    }

    public void setCostoPorSuperficie(BigDecimal costoPorSuperficie) {
        this.costoPorSuperficie = costoPorSuperficie;
    }

    public BigDecimal getCostoPorMontaje() {
        return costoPorMontaje;
    }

    public void setCostoPorMontaje(BigDecimal costoPorMontaje) {
        this.costoPorMontaje = costoPorMontaje;
    }

    public BigDecimal getPlusElectricidad() {
        return plusElectricidad;
    }

    public void setPlusElectricidad(BigDecimal plusElectricidad) {
        this.plusElectricidad = plusElectricidad;
    }

    public BigDecimal getSueldoBase() {
        return sueldoBase;
    }

    public void setSueldoBase(BigDecimal sueldoBase) {
        this.sueldoBase = sueldoBase;
    }

    public BigDecimal getValorPorAnioAntiguedad() {
        return valorPorAnioAntiguedad;
    }

    public void setValorPorAnioAntiguedad(BigDecimal valorPorAnioAntiguedad) {
        this.valorPorAnioAntiguedad = valorPorAnioAntiguedad;
    }

    public BigDecimal getPlusCocinero() {
        return plusCocinero;
    }

    public void setPlusCocinero(BigDecimal plusCocinero) {
        this.plusCocinero = plusCocinero;
    }

    public BigDecimal getPlusAyudante() {
        return plusAyudante;
    }

    public void setPlusAyudante(BigDecimal plusAyudante) {
        this.plusAyudante = plusAyudante;
    }

    public BigDecimal getPlusLavaplatos() {
        return plusLavaplatos;
    }

    public void setPlusLavaplatos(BigDecimal plusLavaplatos) {
        this.plusLavaplatos = plusLavaplatos;
    }
}