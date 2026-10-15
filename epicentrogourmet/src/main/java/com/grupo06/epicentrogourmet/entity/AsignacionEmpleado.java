package com.grupo06.epicentrogourmet.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "asignacion_empleado")
public class AsignacionEmpleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAsignacion;

    @NotNull(message = "El empleado es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_empleado", nullable = false)
    private Empleado empleado;

    @NotNull(message = "La unidad de venta es obligatoria")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_unidad_venta", nullable = false)
    private UnidadVenta unidadVenta;

    @NotNull(message = "La fecha desde es obligatoria")
    private LocalDate fechaDesde;

    private LocalDate fechaHasta;

    private BigDecimal sueldoLiquidado;

    public AsignacionEmpleado() {
    }

    public AsignacionEmpleado(Empleado empleado,
                              UnidadVenta unidadVenta,
                              LocalDate fechaDesde) {

        this.empleado = empleado;
        this.unidadVenta = unidadVenta;
        this.fechaDesde = fechaDesde;
    }

    public boolean estaVigente(LocalDate fecha) {

        if (fecha == null || fechaDesde == null) {
            return false;
        }

        boolean comenzo =
                !fecha.isBefore(fechaDesde);

        boolean noFinalizo =
                fechaHasta == null
                || !fecha.isAfter(fechaHasta);

        return comenzo && noFinalizo;
    }

    public Long getIdAsignacion() {
        return idAsignacion;
    }

    public void setIdAsignacion(Long idAsignacion) {
        this.idAsignacion = idAsignacion;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public UnidadVenta getUnidadVenta() {
        return unidadVenta;
    }

    public void setUnidadVenta(UnidadVenta unidadVenta) {
        this.unidadVenta = unidadVenta;
    }

    public LocalDate getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(LocalDate fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public LocalDate getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(LocalDate fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public BigDecimal getSueldoLiquidado() {
        return sueldoLiquidado;
    }

    public void setSueldoLiquidado(BigDecimal sueldoLiquidado) {
        this.sueldoLiquidado = sueldoLiquidado;
    }

    @Override
    public String toString() {
        return "AsignacionEmpleado [idAsignacion=" + idAsignacion
                + ", fechaDesde=" + fechaDesde
                + ", fechaHasta=" + fechaHasta
                + ", sueldoLiquidado=" + sueldoLiquidado
                + "]";
    }
}