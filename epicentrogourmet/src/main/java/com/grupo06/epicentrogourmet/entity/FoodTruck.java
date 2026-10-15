package com.grupo06.epicentrogourmet.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "food_truck")
@PrimaryKeyJoinColumn(name = "id_unidad_venta")
public class FoodTruck extends UnidadVenta {

    @NotBlank(message = "La patente es obligatoria")
    private String patente;

    private boolean conexionElectrica;

    public FoodTruck() {
        super();
    }

    public FoodTruck(String nombreComercial,
                     Empleado responsable,
                     Double superficie,
                     String codigo,
                     Festival festival,
                     String patente,
                     boolean conexionElectrica) {

        super(
            nombreComercial,
            responsable,
            superficie,
            codigo,
            festival
        );

        this.patente = patente;
        this.conexionElectrica = conexionElectrica;
    }

    @Override
    public BigDecimal calcularCanon(CostosFestival costos) {

        if (costos == null) {
            throw new IllegalArgumentException(
                "Los costos del festival son obligatorios"
            );
        }

        BigDecimal canon =
                BigDecimal.valueOf(getSuperficie())
                        .multiply(costos.getCostoPorSuperficie());

        if (conexionElectrica) {
            canon = canon.add(costos.getPlusElectricidad());
        }

        return canon;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public boolean isConexionElectrica() {
        return conexionElectrica;
    }

    public void setConexionElectrica(boolean conexionElectrica) {
        this.conexionElectrica = conexionElectrica;
    }

    @Override
    public String toString() {
        return "FoodTruck ["
                + super.toString()
                + ", patente=" + patente
                + ", conexionElectrica=" + conexionElectrica
                + "]";
    }
}