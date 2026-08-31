package test;

import java.time.LocalDate;

import negocios.EmpleadoABM;

public class TestEmpleado {
    public static void main(String[] args) {

        try {
         
        	long idCocinero =EmpleadoABM.getInstance().agregarCocinero("david","lopez",41323456,LocalDate.of(1993, 5, 15),LocalDate.of(2023, 1, 10),500000.0,"Parrilla",50000.0);
        	long idCajero = EmpleadoABM.getInstance().agregarCajero("ana","marquez",42927234,LocalDate.of(1998, 11, 20),LocalDate.of(2024, 2, 1),420000.0,"Tarde");
            
            System.out.printf("Cocinero guardado con éxito. ID: %d\n", idCocinero);
            System.out.printf("Cajero guardado con éxito. ID: %d\n", idCajero);

        } catch (Exception e) {
        	System.out.println(e.getMessage());
        }
    }
}
