package test;

import datos.Plato;
import negocios.PlatoABM;

public class TestActualizarPlato {

    public static void main(String[] args) {
        PlatoABM abm = PlatoABM.getInstance();
        long idPlato = 4L;
        double nuevoPrecio = 7500.0;

        try {
            Plato p = abm.traer(idPlato);
            if (p != null) {
                p.setPrecioVenta(nuevoPrecio);
                abm.modificar(p);
                System.out.printf("Plato ID %d actualizado correctamente a $%.2f\n", idPlato, nuevoPrecio);
            } else {
                System.out.println("ERROR: No se encontró el plato con ID " + idPlato);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}