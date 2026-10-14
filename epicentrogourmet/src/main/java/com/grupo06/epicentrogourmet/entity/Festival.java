package com.grupo06.epicentrogourmet.entity;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "festival")
public class Festival {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFestival;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "La temporada es obligatoria")
    private String temporada;

    @NotNull(message = "La fecha de inicio es obligatoria")
    private LocalDate fechaInicio;

    @NotNull(message = "La fecha de fin es obligatoria")
    private LocalDate fechaFin;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_costos")
    private CostosFestival costos;

    public Festival() {
    }

    public Festival(String nombre,
                    String temporada,
                    LocalDate fechaInicio,
                    LocalDate fechaFin) {

        this.nombre = nombre;
        this.temporada = temporada;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    @AssertTrue(
        message = "La fecha de fin debe ser igual o posterior a la fecha de inicio"
    )
    public boolean isPeriodoValido() {

        if (fechaInicio == null || fechaFin == null) {
            return true;
        }

        return !fechaFin.isBefore(fechaInicio);
    }

    public boolean estaActivo(LocalDate fecha) {

        if (fecha == null || fechaInicio == null || fechaFin == null) {
            return false;
        }

        return !fecha.isBefore(fechaInicio)
                && !fecha.isAfter(fechaFin);
    }

    public Long getIdFestival() {
        return idFestival;
    }

    public void setIdFestival(Long idFestival) {
        this.idFestival = idFestival;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTemporada() {
        return temporada;
    }

    public void setTemporada(String temporada) {
        this.temporada = temporada;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public CostosFestival getCostos() {
        return costos;
    }

    public void setCostos(CostosFestival costos) {
        this.costos = costos;
    }

    @Override
    public String toString() {
        return "Festival [idFestival=" + idFestival
                + ", nombre=" + nombre
                + ", temporada=" + temporada
                + ", fechaInicio=" + fechaInicio
                + ", fechaFin=" + fechaFin
                + "]";
    }
}