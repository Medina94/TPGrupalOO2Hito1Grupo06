package com.grupo06.epicentrogourmet.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "unidad_venta")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class UnidadVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUnidadVenta;

    @NotBlank(message = "El nombre comercial es obligatorio")
    private String nombreComercial;

    @NotNull(message = "El responsable es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_responsable", nullable = false)
    private Empleado responsable;

    @NotNull(message = "La superficie es obligatoria")
    @Positive(message = "La superficie debe ser mayor a cero")
    private Double superficie;

    @NotBlank(message = "El código es obligatorio")
    @Size(
        min = 10,
        max = 10,
        message = "El código debe tener exactamente 10 caracteres"
    )
    @Column(unique = true, nullable = false, length = 10)
    private String codigo;

    @NotNull(message = "El festival es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_festival", nullable = false)
    private Festival festival;

    public UnidadVenta() {
    }

    public UnidadVenta(String nombreComercial,
                       Empleado responsable,
                       Double superficie,
                       String codigo,
                       Festival festival) {

        this.nombreComercial = nombreComercial;
        this.responsable = responsable;
        this.superficie = superficie;
        this.codigo = codigo;
        this.festival = festival;
    }

    public abstract BigDecimal calcularCanon(CostosFestival costos);

    public Long getIdUnidadVenta() {
        return idUnidadVenta;
    }

    public void setIdUnidadVenta(Long idUnidadVenta) {
        this.idUnidadVenta = idUnidadVenta;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public Empleado getResponsable() {
        return responsable;
    }

    public void setResponsable(Empleado responsable) {
        this.responsable = responsable;
    }

    public Double getSuperficie() {
        return superficie;
    }

    public void setSuperficie(Double superficie) {
        this.superficie = superficie;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Festival getFestival() {
        return festival;
    }

    public void setFestival(Festival festival) {
        this.festival = festival;
    }

    @Override
    public String toString() {
        return "UnidadVenta [idUnidadVenta=" + idUnidadVenta
                + ", nombreComercial=" + nombreComercial
                + ", superficie=" + superficie
                + ", codigo=" + codigo + "]";
    }
}