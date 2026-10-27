package com.grupo06.epicentrogourmet.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CierreCajaDTO {

    private Long idCierreCaja;

    @NotNull(message = "La fecha es obligatoria")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "La fecha no puede ser futura")
    private LocalDate fecha;

    @NotNull(message = "El monto total es obligatorio")
    @DecimalMin(
            value = "0.0",
            inclusive = true,
            message = "El monto total no puede ser negativo"
    )
    private BigDecimal montoTotal;

    @NotNull(message = "Debe seleccionar un cajero")
    private Long idCajero;

    @NotNull(message = "Debe seleccionar una unidad de venta")
    private Long idUnidadVenta;


    public CierreCajaDTO() {
    }


    public Long getIdCierreCaja() {
        return idCierreCaja;
    }

    public void setIdCierreCaja(Long idCierreCaja) {
        this.idCierreCaja = idCierreCaja;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(BigDecimal montoTotal) {
        this.montoTotal = montoTotal;
    }

    public Long getIdCajero() {
        return idCajero;
    }

    public void setIdCajero(Long idCajero) {
        this.idCajero = idCajero;
    }

    public Long getIdUnidadVenta() {
        return idUnidadVenta;
    }

    public void setIdUnidadVenta(Long idUnidadVenta) {
        this.idUnidadVenta = idUnidadVenta;
    }
}