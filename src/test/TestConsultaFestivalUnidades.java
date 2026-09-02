package test;

import datos.Festival;
import datos.UnidadVenta;
import negocios.FestivalABM;

public class TestConsultaFestivalUnidades {

    public static void main(String[] args) {

        FestivalABM abm = FestivalABM.getInstance();

        try {

            long idFestival = 1;

            Festival festival =
                    abm.traerFestivalYUnidadesDeVenta(idFestival);

            System.out.println("FESTIVAL:");
            System.out.println(festival);

            System.out.println("\nUNIDADES DE VENTA:");

            for (UnidadVenta unidad : festival.getUnidadesDeVenta()) {
                System.out.println(unidad);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}