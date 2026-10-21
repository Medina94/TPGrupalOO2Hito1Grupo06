package com.grupo06.epicentrogourmet.service.interfaces;

import com.grupo06.epicentrogourmet.dto.RentabilidadDTO;
import java.time.LocalDate;

public interface IRentabilidadService {
    // Matamos los puntos 6 y 7 de un solo tiro
    RentabilidadDTO calcularRentabilidad(Long idUnidadVenta, LocalDate fechaDesde, LocalDate fechaHasta);
}