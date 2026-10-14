package com.grupo06.epicentrogourmet.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "puesto_desarmable")
@PrimaryKeyJoinColumn(name = "id_unidad_venta")
public class PuestoDesarmable extends UnidadVenta {

    @Positive(message = "La cantidad de carpas debe ser mayor a cero")
    private int cantidadCarpas;

    @Positive(message = "El tiempo de montaje debe ser mayor a cero")
    private int minutos;

    public PuestoDesarmable() {
        super();
    }

    public PuestoDesarmable(String nombreComercial,
                            Empleado responsable,
                            Double superficie,
                            String codigo,
                            Festival festival,
                            int cantidadCarpas,
                            int minutos) {

        super(
            nombreComercial,
            responsable,
            superficie,
            codigo,
            festival
        );

        this.cantidadCarpas = cantidadCarpas;
        this.minutos = minutos;
    }

    @Override
    public BigDecimal calcularCanon(CostosFestival costos) {

        if (costos == null) {
            throw new IllegalArgumentException(
                "Los costos del festival son obligatorios"
            );
        }

        BigDecimal costoSuperficie =
                BigDecimal.valueOf(getSuperficie())
                        .multiply(costos.getCostoPorSuperficie());

        BigDecimal descuentoMontaje =
                BigDecimal.valueOf(minutos)
                        .multiply(costos.getCostoPorMontaje());

        return costoSuperficie.subtract(descuentoMontaje);
    }

    public int getCantidadCarpas() {
        return cantidadCarpas;
    }

    public void setCantidadCarpas(int cantidadCarpas) {
        this.cantidadCarpas = cantidadCarpas;
    }

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    @Override
    public String toString() {
        return "PuestoDesarmable ["
                + super.toString()
                + ", cantidadCarpas=" + cantidadCarpas
                + ", minutos=" + minutos
                + "]";
    }
}