package com.grupo06.epicentrogourmet.dto;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class RentabilidadDTO {
    private String nombreUnidad;
    private BigDecimal ingresosPedidos = BigDecimal.ZERO;
    private BigDecimal costosPlatos = BigDecimal.ZERO;
    private BigDecimal costosSueldos = BigDecimal.ZERO;
    private BigDecimal costoCanon = BigDecimal.ZERO;
    
    // El resultado final: Ingresos - Costo de platos - Sueldos - Canon
    private BigDecimal rentabilidadNeta = BigDecimal.ZERO;
}