package com.grupo06.epicentrogourmet.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public class PlatoDTO {
    private Long idPlato;

    @NotBlank(message = "El nombre del plato es obligatorio")
    private String nombre;

    @NotNull(message = "El precio de venta es obligatorio")
    @DecimalMin(
            value = "0.0",
            inclusive = true,
            message = "El precio de venta no puede ser negativo"
    )
    private BigDecimal precioVenta;

    @NotNull(message = "El costo de producción es obligatorio")
    @DecimalMin(
            value = "0.0",
            inclusive = true,
            message = "El costo de producción no puede ser negativo"
    )
    private BigDecimal costoProduccion;

    public Long getIdPlato() {
        return idPlato;
    }

    public void setIdPlato(Long idPlato) {
        this.idPlato = idPlato;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = precioVenta;
    }

    public BigDecimal getCostoProduccion() {
        return costoProduccion;
    }

    public void setCostoProduccion(BigDecimal costoProduccion) {
        this.costoProduccion = costoProduccion;
    }
}
