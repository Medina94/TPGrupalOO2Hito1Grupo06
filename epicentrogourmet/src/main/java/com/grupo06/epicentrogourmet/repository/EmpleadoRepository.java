package com.grupo06.epicentrogourmet.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grupo06.epicentrogourmet.entity.Empleado;

@Repository
public interface EmpleadoRepository
        extends JpaRepository<Empleado, Long> {

    boolean existsByDni(int dni);

    boolean existsByEmail(String email);

    List<Empleado> findAllByOrderByDniAsc();

    List<Empleado> findByDni(int dni);

    Optional<Empleado> findByEmail(String email);
}