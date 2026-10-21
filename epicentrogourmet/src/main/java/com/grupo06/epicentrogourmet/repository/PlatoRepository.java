package com.grupo06.epicentrogourmet.repository;

import java.util.List;

import com.grupo06.epicentrogourmet.entity.Plato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatoRepository extends JpaRepository<Plato, Long> {

    Plato findByIdPlato(Long idPlato);

    List<Plato> findAllByUnidadVenta_IdUnidadVenta(Long idUnidadVenta);
}
