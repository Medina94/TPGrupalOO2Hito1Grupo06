package test;

import java.time.LocalDate;

import datos.Festival;
import negocios.FestivalABM;

public class TestActualizarFestival {

    public static void main(String[] args) {

        FestivalABM abm = FestivalABM.getInstance();

        try {

            // Traemos de la BD el festival con id 1
            Festival festival = abm.traer(1L);

            System.out.println("ANTES:");
            System.out.println(festival);

            // Modificamos algunos datos
            festival.setTemporada("primavera");
            festival.setFechaFin(LocalDate.of(2026, 10, 15));

            // Guardamos los cambios en la BD
            abm.modificar(festival);

            // Lo volvemos a traer para comprobar
            Festival festivalActualizado = abm.traer(1L);

            System.out.println("DESPUES:");
            System.out.println(festivalActualizado);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}