package com.grupo06.epicentrogourmet.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grupo06.epicentrogourmet.dto.CostosFestivalDTO;
import com.grupo06.epicentrogourmet.entity.CostosFestival;
import com.grupo06.epicentrogourmet.entity.Festival;
import com.grupo06.epicentrogourmet.repository.CostosFestivalRepository;
import com.grupo06.epicentrogourmet.repository.FestivalRepository;

@Service
public class CostosFestivalService {

    private final CostosFestivalRepository costosFestivalRepository;
    private final FestivalRepository festivalRepository;

    public CostosFestivalService(
            CostosFestivalRepository costosFestivalRepository,
            FestivalRepository festivalRepository) {

        this.costosFestivalRepository = costosFestivalRepository;
        this.festivalRepository = festivalRepository;
    }

    @Transactional
    public CostosFestival guardarCostos(
            Long idFestival,
            CostosFestivalDTO dto) {

        Festival festival = festivalRepository
                .findById(idFestival)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "El Festival seleccionado no existe."
                        )
                );

        CostosFestival costos = festival.getCostos();

        // Si el Festival todavía no tiene costos,
        // se crea una nueva configuración.
        if (costos == null) {
            costos = new CostosFestival();
        }

        costos.setCostoPorSuperficie(
                dto.getCostoPorSuperficie()
        );

        costos.setCostoPorMontaje(
                dto.getCostoPorMontaje()
        );

        costos.setPlusElectricidad(
                dto.getPlusElectricidad()
        );

        costos.setSueldoBase(
                dto.getSueldoBase()
        );

        costos.setValorPorAnioAntiguedad(
                dto.getValorPorAnioAntiguedad()
        );

        costos.setPlusCocinero(
                dto.getPlusCocinero()
        );

        costos.setPlusAyudante(
                dto.getPlusAyudante()
        );

        costos.setPlusLavaplatos(
                dto.getPlusLavaplatos()
        );

        costos = costosFestivalRepository.save(costos);

        festival.setCostos(costos);
        festivalRepository.save(festival);

        return costos;
    }


    @Transactional(readOnly = true)
    public CostosFestivalDTO obtenerCostosDTO(Long idFestival) {

        Festival festival = festivalRepository
                .findById(idFestival)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "El Festival seleccionado no existe."
                        )
                );

        CostosFestivalDTO dto =
                new CostosFestivalDTO();

        CostosFestival costos =
                festival.getCostos();

        if (costos != null) {

            dto.setCostoPorSuperficie(
                    costos.getCostoPorSuperficie()
            );

            dto.setCostoPorMontaje(
                    costos.getCostoPorMontaje()
            );

            dto.setPlusElectricidad(
                    costos.getPlusElectricidad()
            );

            dto.setSueldoBase(
                    costos.getSueldoBase()
            );

            dto.setValorPorAnioAntiguedad(
                    costos.getValorPorAnioAntiguedad()
            );

            dto.setPlusCocinero(
                    costos.getPlusCocinero()
            );

            dto.setPlusAyudante(
                    costos.getPlusAyudante()
            );

            dto.setPlusLavaplatos(
                    costos.getPlusLavaplatos()
            );
        }

        return dto;
    }
}