package com.grupo06.epicentrogourmet.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PlatoDTO {
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
}
