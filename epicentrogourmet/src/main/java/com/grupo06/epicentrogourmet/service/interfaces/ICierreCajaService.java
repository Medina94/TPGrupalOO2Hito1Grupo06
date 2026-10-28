
package com.grupo06.epicentrogourmet.service.interfaces;

import com.grupo06.epicentrogourmet.dto.CierreCajaDTO;

import java.time.LocalDate;
import java.util.List;

public interface ICierreCajaService {
    List<CierreCajaDTO> listarCierres();

    void guardarCierre(CierreCajaDTO cierreCajaDTO);

    byte[] generarPdfCierre(Long id);
    
    List<CierreCajaDTO> listarCierresPorFecha(
            LocalDate fechaDesde,
            LocalDate fechaHasta
    );
}
