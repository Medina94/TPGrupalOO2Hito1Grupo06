package com.grupo06.epicentrogourmet.service;

import com.grupo06.epicentrogourmet.dto.ReciboSueldoDTO;

public interface IPdfService {

    /**
     * Genera un PDF con el listado de empleados de la Unidad del Responsable autenticado.
     */
    byte[] generarListadoEmpleados();

    /**
     * Genera un PDF con el recibo de sueldo.
     */
    byte[] generarReciboSueldo(ReciboSueldoDTO recibo);
}