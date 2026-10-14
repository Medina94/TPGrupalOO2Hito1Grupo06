package com.grupo06.epicentrogourmet.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(
    name = "cierre_caja",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_cierre_unidad_fecha",
            columnNames = {"id_unidad_venta", "fecha"}
        )
    }
)
public class CierreCaja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCierreCaja;

    @NotNull(message = "La fecha es obligatoria")
    @Column(nullable = false)
    private LocalDate fecha;

    @NotNull(message = "El cajero es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cajero", nullable = false)
    private Cajero cajero;

    @NotNull(message = "La unidad de venta es obligatoria")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_unidad_venta", nullable = false)
    private UnidadVenta unidadVenta;

    @NotNull(message = "El monto total es obligatorio")
    @DecimalMin(
        value = "0.0",
        inclusive = true,
        message = "El monto total no puede ser negativo"
    )
    @Column(nullable = false)
    private BigDecimal montoTotal;

    public CierreCaja() {
    }

    public CierreCaja(LocalDate fecha,
                      Cajero cajero,
                      UnidadVenta unidadVenta,
                      BigDecimal montoTotal) {

        this.fecha = fecha;
        this.cajero = cajero;
        this.unidadVenta = unidadVenta;
        this.montoTotal = montoTotal;
    }

    public Long getIdCierreCaja() {
        return idCierreCaja;
    }

    public void setIdCierreCaja(Long idCierreCaja) {
        this.idCierreCaja = idCierreCaja;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Cajero getCajero() {
        return cajero;
    }

    public void setCajero(Cajero cajero) {
        this.cajero = cajero;
    }

    public UnidadVenta getUnidadVenta() {
        return unidadVenta;
    }

    public void setUnidadVenta(UnidadVenta unidadVenta) {
        this.unidadVenta = unidadVenta;
    }

    public BigDecimal getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(BigDecimal montoTotal) {
        this.montoTotal = montoTotal;
    }

    @Override
    public String toString() {
        return "CierreCaja [idCierreCaja=" + idCierreCaja
                + ", fecha=" + fecha
                + ", montoTotal=" + montoTotal
                + "]";
    }
}