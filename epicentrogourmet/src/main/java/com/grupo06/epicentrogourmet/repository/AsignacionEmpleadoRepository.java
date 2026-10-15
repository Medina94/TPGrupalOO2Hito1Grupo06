package com.grupo06.epicentrogourmet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grupo06.epicentrogourmet.entity.AsignacionEmpleado;
import com.grupo06.epicentrogourmet.entity.Empleado;

@Repository
public interface AsignacionEmpleadoRepository
        extends JpaRepository<AsignacionEmpleado, Long> {

    List<AsignacionEmpleado> findByEmpleado(Empleado empleado);
}