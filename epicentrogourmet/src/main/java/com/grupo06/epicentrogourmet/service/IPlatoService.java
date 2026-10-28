package com.grupo06.epicentrogourmet.service;

import com.grupo06.epicentrogourmet.dto.PlatoDTO;

import java.util.List;

public interface IPlatoService {

    /**
     * Metodo para listar los platos de la unidad de venta del usuario actual.
     * @return List<PlatoDTO>
     */
    List<PlatoDTO> listarPlatos();

    /**
     * Busca el plato incluido en la mayor cantidad de pedidos
     * de una unidad y festival.
     * @param idUnidadVenta identificador de la unidad de venta
     * @param idFestival identificador del festival
     * @return PlatoDTO ganador o null si no hay pedidos
     */
    PlatoDTO obtenerPlatoEstrella(Long idUnidadVenta, Long idFestival);

    /**
     * Metodo para agregar un plato a la BD.
     * @param platoDTO
     */
    void agregarPlato(PlatoDTO platoDTO);

    /**
     * Actualiza un plato perteneciente a la unidad de venta del usuario actual.
     * @param idPlato identificador del plato
     * @param platoDTO datos actualizados
     */
    void actualizarPlato(Long idPlato, PlatoDTO platoDTO);

    /**
     * Metodo para eliminar un plato de la BD.
     * @param idPlato
     */
    void eliminarPlato(Long idPlato);

}
