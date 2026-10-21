package test;

import datos.Cajero;
import datos.Cocinero;
import datos.Empleado;
import negocios.EmpleadoABM;

public class TestConsultaHerenciaEmpleado {

    public static void main(String[] args) {

        EmpleadoABM abm = EmpleadoABM.getInstance();

        try {

            // Consultamos dos empleados existentes
            Empleado empleado1 = abm.traer(1L);
            Empleado empleado2 = abm.traer(2L);

            mostrarEmpleado(empleado1);
            mostrarEmpleado(empleado2);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void mostrarEmpleado(Empleado empleado) {

        if (empleado == null) {
            System.out.println("Empleado no encontrado");
            return;
        }

        System.out.println("\nEMPLEADO:");
        System.out.println(empleado);

        if (empleado instanceof Cocinero) {

            Cocinero cocinero = (Cocinero) empleado;

            System.out.println("Tipo: COCINERO");
            System.out.println(
                    "Especialidad culinaria: "
                    + cocinero.getEspecialidadCulinaria()
            );
            System.out.println(
                    "Plus: "
                    + cocinero.getPlus()
            );

        } else if (empleado instanceof Cajero) {

            Cajero cajero = (Cajero) empleado;

            System.out.println("Tipo: CAJERO");
            System.out.println(
                    "Turno: "
                    + cajero.getTurno()
            );
        }
    }
}