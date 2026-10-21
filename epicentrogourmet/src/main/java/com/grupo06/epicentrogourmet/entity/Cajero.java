package com.grupo06.epicentrogourmet.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "cajero")
@PrimaryKeyJoinColumn(name = "id_empleado")
public class Cajero extends Empleado {

    @NotNull(message = "El turno es obligatorio")
    @Enumerated(EnumType.STRING)
    private Turno turno;

    public Cajero() {
        super();
    }

    public Cajero(String nombre,
                  String apellido,
                  int dni,
                  String email,
                  LocalDate fechaNacimiento,
                  LocalDate fechaIngreso,
                  Turno turno) {

        super(nombre, apellido, dni, email, fechaNacimiento, fechaIngreso);
        this.turno = turno;
    }

    @Override
    public BigDecimal liquidarHaberes(Festival festival,
                                      CostosFestival costos) {

        if (festival == null || costos == null) {
            throw new IllegalArgumentException(
                    "El festival y los costos son obligatorios"
            );
        }

        int antiguedad =
                calcularAntiguedad(festival.getFechaInicio());

        BigDecimal montoAntiguedad =
                costos.getValorPorAnioAntiguedad()
                        .multiply(BigDecimal.valueOf(antiguedad));

        BigDecimal baseMasAntiguedad =
                costos.getSueldoBase()
                        .add(montoAntiguedad);

        long diasFestival =
                ChronoUnit.DAYS.between(
                        festival.getFechaInicio(),
                        festival.getFechaFin()
                ) + 1;

        int cantidadTramos =
                (int) Math.ceil(diasFestival / 15.0);

        return baseMasAntiguedad
                .multiply(BigDecimal.valueOf(0.5))
                .multiply(BigDecimal.valueOf(cantidadTramos));
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    @Override
    public String toString() {
        return "Cajero ["
                + super.toString()
                + ", turno=" + turno
                + "]";
    }
}