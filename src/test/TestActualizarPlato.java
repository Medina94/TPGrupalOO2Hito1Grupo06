package test;

import negocios.PlatoABM;

public class TestActualizarPlato {

    public static void main(String[] args) {
        PlatoABM abm = new PlatoABM();
        long idPlato = 1L;
        double nuevoPrecio = 7500.0;

        try {
            abm.modificarPrecio(idPlato, nuevoPrecio);
            System.out.printf("Plato ID %d actualizado correctamente a $%.2f\n", idPlato, nuevoPrecio);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}