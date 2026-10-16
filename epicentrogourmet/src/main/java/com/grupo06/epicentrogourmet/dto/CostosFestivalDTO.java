package com.grupo06.epicentrogourmet.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class CostosFestivalDTO {

    @NotNull(message = "El costo por superficie es obligatorio.")
    @DecimalMin(value = "0.0", inclusive = true,
            message = "El costo por superficie no puede ser negativo.")
    private BigDecimal costoPorSuperficie;

    @NotNull(message = "El costo por montaje es obligatorio.")
    @DecimalMin(value = "0.0", inclusive = true,
            message = "El costo por montaje no puede ser negativo.")
    private BigDecimal costoPorMontaje;

    @NotNull(message = "El plus por electricidad es obligatorio.")
    @DecimalMin(value = "0.0", inclusive = true,
            message = "El plus por electricidad no puede ser negativo.")
    private BigDecimal plusElectricidad;

    @NotNull(message = "El sueldo base es obligatorio.")
    @DecimalMin(value = "0.0", inclusive = true,
            message = "El sueldo base no puede ser negativo.")
    private BigDecimal sueldoBase;

    @NotNull(message = "El valor por año de antigüedad es obligatorio.")
    @DecimalMin(value = "0.0", inclusive = true,
            message = "El valor por antigüedad no puede ser negativo.")
    private BigDecimal valorPorAnioAntiguedad;

    @NotNull(message = "El plus de cocinero es obligatorio.")
    @DecimalMin(value = "0.0", inclusive = true,
            message = "El plus de cocinero no puede ser negativo.")
    private BigDecimal plusCocinero;

    @NotNull(message = "El plus de ayudante es obligatorio.")
    @DecimalMin(value = "0.0", inclusive = true,
            message = "El plus de ayudante no puede ser negativo.")
    private BigDecimal plusAyudante;

    @NotNull(message = "El plus de lavaplatos es obligatorio.")
    @DecimalMin(value = "0.0", inclusive = true,
            message = "El plus de lavaplatos no puede ser negativo.")
    private BigDecimal plusLavaplatos;


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