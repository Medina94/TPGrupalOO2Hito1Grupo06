package com.grupo06.epicentrogourmet.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grupo06.epicentrogourmet.dto.ReporteMayoresCanonDTO;
import com.grupo06.epicentrogourmet.entity.FoodTruck;
import com.grupo06.epicentrogourmet.entity.PuestoDesarmable;
import com.grupo06.epicentrogourmet.entity.UnidadVenta;

@Service
public class ReporteCanonService {

    private final UnidadVentaService unidadVentaService;

    public ReporteCanonService(
            UnidadVentaService unidadVentaService) {

        this.unidadVentaService = unidadVentaService;
    }


    @Transactional(readOnly = true)
    public List<ReporteMayoresCanonDTO> obtenerMayoresCanones(
            Long idFestival) {

        List<UnidadVenta> unidades =
                unidadVentaService.listarPorFestival(idFestival);

        return unidades.stream()

                // Solo podemos incluir unidades cuyo Festival
                // tenga costos configurados.
                .filter(unidad ->
                        unidad.getFestival() != null
                        && unidad.getFestival().getCostos() != null
                )

                // Convertimos cada UnidadVenta al DTO del reporte.
                .map(unidad ->
                        new ReporteMayoresCanonDTO(
                                unidad.getIdUnidadVenta(),
                                unidad.getNombreComercial(),
                                unidad.getCodigo(),
                                obtenerTipoUnidad(unidad),
                                unidadVentaService.calcularCanon(
                                        unidad.getIdUnidadVenta()
                                )
                        )
                )

                // Ordenamos de mayor canon a menor canon.
                .sorted(
                        Comparator.comparing(
                                ReporteMayoresCanonDTO::getCanon
                        ).reversed()
                )

                .toList();
    }


    private String obtenerTipoUnidad(
            UnidadVenta unidad) {

        if (unidad instanceof FoodTruck) {
            return "Food Truck";
        }

        if (unidad instanceof PuestoDesarmable) {
            return "Puesto Desarmable";
        }

        return "Unidad de Venta";
    }
}