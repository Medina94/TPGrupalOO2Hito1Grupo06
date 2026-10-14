package com.grupo06.epicentrogourmet.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPedido;

    @NotNull
    private LocalDate fecha;

    @NotNull(message = "El festival es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_festival", nullable = false)
    private Festival festival;

    @NotNull(message = "La unidad de venta es obligatoria")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_unidad_venta", nullable = false)
    private UnidadVenta unidadVenta;

    @Valid
    @NotEmpty(message = "El pedido debe contener al menos un item")
    @OneToMany(
        mappedBy = "pedido",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<ItemPedido> items = new ArrayList<>();

    public Pedido() {
    }

    public Pedido(Festival festival, UnidadVenta unidadVenta) {
        this.fecha = LocalDate.now();
        this.festival = festival;
        this.unidadVenta = unidadVenta;
    }

    @PrePersist
    public void asignarFechaActual() {
        this.fecha = LocalDate.now();
    }

    public void agregarItem(ItemPedido item) {
        item.setPedido(this);
        items.add(item);
    }

    public void eliminarItem(ItemPedido item) {
        items.remove(item);
        item.setPedido(null);
    }

    public BigDecimal calcularTotalVenta() {
        return items.stream()
                .map(ItemPedido::calcularSubtotalVenta)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal calcularCostoTotal() {
        return items.stream()
                .map(ItemPedido::calcularSubtotalCosto)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Festival getFestival() {
        return festival;
    }

    public void setFestival(Festival festival) {
        this.festival = festival;
    }

    public UnidadVenta getUnidadVenta() {
        return unidadVenta;
    }

    public void setUnidadVenta(UnidadVenta unidadVenta) {
        this.unidadVenta = unidadVenta;
    }

    public List<ItemPedido> getItems() {
        return items;
    }

    public void setItems(List<ItemPedido> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Pedido [idPedido=" + idPedido
                + ", fecha=" + fecha + "]";
    }
}