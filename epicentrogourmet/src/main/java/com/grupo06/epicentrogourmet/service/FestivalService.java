package com.grupo06.epicentrogourmet.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grupo06.epicentrogourmet.entity.Festival;
import com.grupo06.epicentrogourmet.repository.FestivalRepository;

@Service
public class FestivalService {

    private final FestivalRepository festivalRepository;

    public FestivalService(FestivalRepository festivalRepository) {
        this.festivalRepository = festivalRepository;
    }

    @Transactional(readOnly = true)
    public List<Festival> listarFestivales() {
        return festivalRepository.findAllByOrderByFechaInicioAsc();
    }

    @Transactional(readOnly = true)
    public Festival buscarPorId(Long idFestival) {

        return festivalRepository
                .findById(idFestival)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "El festival seleccionado no existe."
                        )
                );
    }
}