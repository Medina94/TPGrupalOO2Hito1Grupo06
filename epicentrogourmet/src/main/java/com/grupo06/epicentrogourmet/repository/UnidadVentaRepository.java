package com.grupo06.epicentrogourmet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grupo06.epicentrogourmet.entity.UnidadVenta;

@Repository
public interface UnidadVentaRepository
        extends JpaRepository<UnidadVenta, Long> {

    boolean existsByCodigo(String codigo);

    List<UnidadVenta> findByResponsable_IdEmpleado(Long idEmpleado);

    List<UnidadVenta>
            findByFestival_IdFestivalOrderByNombreComercialAsc(
                    Long idFestival
            );
}