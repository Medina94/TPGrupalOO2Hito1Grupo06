package test;

import datos.FoodTruck;
import datos.PuestoDesarmable;
import datos.UnidadVenta;
import negocios.UnidadVentaABM;

public class TestConsultaUnidadVentaPorCodigo {

    public static void main(String[] args) {

        UnidadVentaABM abm = UnidadVentaABM.getInstance();

        try {

            // Consultamos las dos unidades de venta que cargamos anteriormente
            UnidadVenta unidad1 = abm.traer("23212df214");
            UnidadVenta unidad2 = abm.traer("23jd921312");

            mostrarUnidadVenta(unidad1);
            mostrarUnidadVenta(unidad2);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void mostrarUnidadVenta(UnidadVenta unidad) {

        if (unidad == null) {
            System.out.println("Unidad de venta no encontrada");
            return;
        }

        System.out.println("\nUNIDAD DE VENTA:");
        System.out.println(unidad);

        if (unidad instanceof PuestoDesarmable) {
            System.out.println("Tipo: PUESTO DESARMABLE");

        } else if (unidad instanceof FoodTruck) {
            System.out.println("Tipo: FOOD TRUCK");

        } else {
            System.out.println("Tipo: UNIDAD DE VENTA");
        }
    }
}