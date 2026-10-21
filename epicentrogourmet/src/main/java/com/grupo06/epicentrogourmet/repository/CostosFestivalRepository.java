package com.grupo06.epicentrogourmet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grupo06.epicentrogourmet.entity.CostosFestival;

@Repository
public interface CostosFestivalRepository
        extends JpaRepository<CostosFestival, Long> {

}