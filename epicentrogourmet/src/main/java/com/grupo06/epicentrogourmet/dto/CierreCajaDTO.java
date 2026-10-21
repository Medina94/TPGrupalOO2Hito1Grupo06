package com.grupo06.epicentrogourmet.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class CierreCajaDTO {
    
    private Long idCierreCaja;

    @NotNull(message = "La fecha es obligatoria")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "La fecha no puede ser futura")
    private LocalDate fecha;

    @NotNull(message = "El monto total es obligatorio")
    @DecimalMin(value = "0.0", inclusive = true, message = "El monto total no puede ser negativo")
    private BigDecimal montoTotal;

    @NotNull(message = "Debe seleccionar un cajero")
    private Long idCajero;

    @NotNull(message = "Debe seleccionar una unidad de venta")
    private Long idUnidadVenta;
}