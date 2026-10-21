package com.grupo06.epicentrogourmet.service.interfaces;

import com.grupo06.epicentrogourmet.dto.CierreCajaDTO;
import java.util.List;

public interface ICierreCajaService {
    List<CierreCajaDTO> listarCierres();
    void guardarCierre(CierreCajaDTO cierreCajaDTO);
}