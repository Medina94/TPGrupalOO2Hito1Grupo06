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
