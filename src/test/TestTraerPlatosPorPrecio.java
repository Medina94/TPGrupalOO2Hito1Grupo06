package test;

import java.util.List;
import datos.Plato;
import negocios.PlatoABM;

public class TestTraerPlatosPorPrecio {

    public static void main(String[] args) {
        PlatoABM abm = new PlatoABM();
        double precioMinimo = 4000.0;

        try {
            List<Plato> lista = abm.traerPrecioMayorA(precioMinimo);
            System.out.println("=== PLATOS CON PRECIO MAYOR A $" + precioMinimo + " ===");
            for (Plato p : lista) {
                System.out.printf("ID: %d | Nombre: %s | Precio: $%.2f\n", 
                        p.getIdPlato(), p.getNombre(), p.getPrecioVenta());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}