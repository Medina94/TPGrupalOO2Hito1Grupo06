package com.grupo06.epicentrogourmet.service;

import com.grupo06.epicentrogourmet.dto.RegistroEmpleadoDTO;
import com.grupo06.epicentrogourmet.dto.RegistroUnidadResponsableDTO;

public interface IMailService {
    /**
     * Metodo asincrono para enviar un mail de alta de empleado.
     * @param registroEmpleadoDTO
     * @param username
     * @param password
     */
    void enviarMailAlta(RegistroEmpleadoDTO registroEmpleadoDTO, String username, String password);

    /**
     * Metodo asincrono para enviar un mail de alta de responsable de unidad.
     * @param registroUnidadResponsableDTO
     * @param username
     * @param password
     */
    void enviarMailAltaResponsable(
            RegistroUnidadResponsableDTO registroUnidadResponsableDTO,
            String username,
            String password);

    /**
     * Metodo asincrono para enviar un mail de reactivación de empleado.
     */

    void enviarMailReactivacion(String email);
}
