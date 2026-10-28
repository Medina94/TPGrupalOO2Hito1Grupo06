package com.grupo06.epicentrogourmet.repository;

import com.grupo06.epicentrogourmet.entity.Plato;

public interface PlatoEstrellaProjection {

    Plato getPlato();

    Long getCantidadPedidos();
}
