package test;

import java.time.LocalDate;
<<<<<<< HEAD

=======
import datos.Cajero;
import datos.Cocinero;
>>>>>>> c8e297fe96c8bc76cef998c427cea5863f7bc759
import negocios.EmpleadoABM;

public class TestEmpleado {
    public static void main(String[] args) {
<<<<<<< HEAD

        try {
         
        	long idCocinero =EmpleadoABM.getInstance().agregarCocinero("david","lopez",41323456,LocalDate.of(1993, 5, 15),LocalDate.of(2023, 1, 10),500000.0,"Parrilla",50000.0);
        	long idCajero = EmpleadoABM.getInstance().agregarCajero("ana","marquez",42927234,LocalDate.of(1998, 11, 20),LocalDate.of(2024, 2, 1),420000.0,"Tarde");
            
            System.out.printf("Cocinero guardado con éxito. ID: %d\n", idCocinero);
            System.out.printf("Cajero guardado con éxito. ID: %d\n", idCajero);

        } catch (Exception e) {
        	System.out.println(e.getMessage());
=======
        EmpleadoABM eAbm = new EmpleadoABM();

        try {
         
            Cocinero cocinero = new Cocinero(30,"nahuel","rodriguez",42323456,LocalDate.of(1993, 5, 15),LocalDate.of(2023, 1, 10),500000.0,"Parrilla",50000.0);

            Cajero cajero = new Cajero(25,"mica","gonzalez",42987234,LocalDate.of(1998, 11, 20),LocalDate.of(2024, 2, 1),420000.0,"Tarde");

     
            long idCocinero = eAbm.agregar(cocinero);
            System.out.printf("Cocinero guardado con éxito. ID: %d\n", idCocinero);

            long idCajero = eAbm.agregar(cajero);
            System.out.printf("Cajero guardado con éxito. ID: %d\n", idCajero);

        } catch (Exception e) {
            System.out.println("Error al agregar empleado: " + e.getMessage());
            e.printStackTrace();
>>>>>>> c8e297fe96c8bc76cef998c427cea5863f7bc759
        }
    }
}
