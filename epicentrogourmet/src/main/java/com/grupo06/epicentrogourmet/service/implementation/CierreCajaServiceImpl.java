package com.grupo06.epicentrogourmet.service.implementation;

import com.grupo06.epicentrogourmet.dto.CierreCajaDTO;
import com.grupo06.epicentrogourmet.entity.Cajero;
import com.grupo06.epicentrogourmet.entity.CierreCaja;
import com.grupo06.epicentrogourmet.entity.UnidadVenta;
import com.grupo06.epicentrogourmet.repository.ICierreCajaRepository;
import com.grupo06.epicentrogourmet.repository.UnidadVentaRepository;
import com.grupo06.epicentrogourmet.service.interfaces.ICierreCajaService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CierreCajaServiceImpl implements ICierreCajaService {

    private final ICierreCajaRepository cierreCajaRepository;
    private final ModelMapper modelMapper;
    private final UnidadVentaRepository unidadVentaRepository;

    public CierreCajaServiceImpl(ICierreCajaRepository cierreCajaRepository, 
                                 ModelMapper modelMapper, 
                                 UnidadVentaRepository unidadVentaRepository) {
        this.cierreCajaRepository = cierreCajaRepository;
        this.modelMapper = modelMapper;
        this.unidadVentaRepository = unidadVentaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CierreCajaDTO> listarCierres() {
        return cierreCajaRepository.findAll().stream()
                .map(cierre -> modelMapper.map(cierre, CierreCajaDTO.class))
                .toList();
    }

    @Override
    @Transactional
    public void guardarCierre(CierreCajaDTO cierreCajaDTO) {
        // ARMAMOS EL OBJETO A MANO PARA EVITAR QUE MODELMAPPER EXPLOTE CON LOS IDs CRUZADOS
        CierreCaja cierre = new CierreCaja();
        cierre.setFecha(cierreCajaDTO.getFecha());
        cierre.setMontoTotal(cierreCajaDTO.getMontoTotal());
        
        // Asignamos el cajero
        Cajero cajero = new Cajero();
        cajero.setIdEmpleado(cierreCajaDTO.getIdCajero());
        cierre.setCajero(cajero);

        // Buscamos la unidad de venta
        UnidadVenta unidad = unidadVentaRepository.findById(cierreCajaDTO.getIdUnidadVenta())
                .orElseThrow(() -> new IllegalArgumentException("La Unidad de Venta ingresada no existe."));
        cierre.setUnidadVenta(unidad);

        // Guardamos en la base de datos
        cierreCajaRepository.save(cierre);
    }
}