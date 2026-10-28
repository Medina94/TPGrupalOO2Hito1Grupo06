package com.grupo06.epicentrogourmet.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "responsable")
@PrimaryKeyJoinColumn(name = "id_empleado")
public class Responsable extends Empleado {

    public Responsable() {
        super();
    }

    public Responsable(
            String nombre,
            String apellido,
            int dni,
            String email,
            LocalDate fechaNacimiento,
            LocalDate fechaIngreso) {

        super(
                nombre,
                apellido,
                dni,
                email,
                fechaNacimiento,
                fechaIngreso
        );
    }

    /*
     * El Responsable administra la Unidad de Venta.
     * No cumple obligatoriamente una función operativa
     * de Cajero o Cocinero.
     *
     * Por eso no se liquidan haberes automáticamente
     * mediante las reglas de los empleados operativos.
     */
    @Override
    public BigDecimal liquidarHaberes(
            Festival festival,
            CostosFestival costos) {

        return BigDecimal.ZERO;
    }

    @Override
    public String toString() {
        return "Responsable ["
                + super.toString()
                + "]";
    }
}