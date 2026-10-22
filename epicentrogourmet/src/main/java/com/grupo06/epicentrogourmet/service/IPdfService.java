package com.grupo06.epicentrogourmet.service;

public interface IPdfService {

    /**
     * Genera un PDF con el listado de empleados de la Unidad del Responsable autenticado.
     */
    byte[] generarListadoEmpleados();
}