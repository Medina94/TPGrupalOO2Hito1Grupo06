package com.grupo06.epicentrogourmet.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "cocinero")
@PrimaryKeyJoinColumn(name = "id_empleado")
public class Cocinero extends Empleado {

    @NotNull(message = "El rol de cocina es obligatorio")
    @Enumerated(EnumType.STRING)
    private RolCocina rolCocina;

    public Cocinero() {
        super();
    }

    public Cocinero(String nombre,
                    String apellido,
                    int dni,
                    String email,
                    java.time.LocalDate fechaNacimiento,
                    java.time.LocalDate fechaIngreso,
                    RolCocina rolCocina) {

        super(nombre, apellido, dni, email, fechaNacimiento, fechaIngreso);
        this.rolCocina = rolCocina;
    }

    @Override
    public BigDecimal liquidarHaberes(Festival festival,
                                      CostosFestival costos) {

        if (festival == null || costos == null) {
            throw new IllegalArgumentException(
                    "El festival y los costos son obligatorios"
            );
        }

        BigDecimal plusCategoria;

        switch (rolCocina) {
            case COCINERO:
                plusCategoria = costos.getPlusCocinero();
                break;

            case AYUDANTE:
                plusCategoria = costos.getPlusAyudante();
                break;

            case LAVAPLATOS:
                plusCategoria = costos.getPlusLavaplatos();
                break;

            default:
                throw new IllegalStateException(
                        "Rol de cocina no válido"
                );
        }

        long diasFestival =
                java.time.temporal.ChronoUnit.DAYS.between(
                        festival.getFechaInicio(),
                        festival.getFechaFin()
                ) + 1;

        int cantidadTramos =
                (int) Math.ceil(diasFestival / 15.0);

        BigDecimal baseMasPlus =
                costos.getSueldoBase()
                        .add(plusCategoria);

        return baseMasPlus
                .multiply(BigDecimal.valueOf(0.5))
                .multiply(BigDecimal.valueOf(cantidadTramos));
    }

    public RolCocina getRolCocina() {
        return rolCocina;
    }

    public void setRolCocina(RolCocina rolCocina) {
        this.rolCocina = rolCocina;
    }

    @Override
    public String toString() {
        return "Cocinero ["
                + super.toString()
                + ", rolCocina=" + rolCocina
                + "]";
    }
}