package com.grupo06.epicentrogourmet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grupo06.epicentrogourmet.entity.Festival;

@Repository
public interface FestivalRepository extends JpaRepository<Festival, Long> {

    List<Festival> findAllByOrderByFechaInicioAsc();

}