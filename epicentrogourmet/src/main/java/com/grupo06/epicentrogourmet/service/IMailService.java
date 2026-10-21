package com.grupo06.epicentrogourmet.service;

import com.grupo06.epicentrogourmet.dto.RegistroEmpleadoDTO;

public interface IMailService {
    /**
     * Metodo asincrono para enviar un mail de alta de empleado.
     * @param registroEmpleadoDTO
     * @param username
     */
    void enviarMailAlta(RegistroEmpleadoDTO registroEmpleadoDTO, String username);
}
