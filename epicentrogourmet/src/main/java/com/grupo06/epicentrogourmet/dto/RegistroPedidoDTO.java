package com.grupo06.epicentrogourmet.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class RegistroPedidoDTO {

    @NotNull(message = "Debe seleccionar un plato")
    private Long idPlato;

    @Min(
        value = 1,
        message = "La cantidad debe ser mayor a cero"
    )
    private int cantidad = 1;

    public RegistroPedidoDTO() {
    }

    public Long getIdPlato() {
        return idPlato;
    }

    public void setIdPlato(Long idPlato) {
        this.idPlato = idPlato;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}