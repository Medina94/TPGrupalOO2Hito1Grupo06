package test;

import datos.Empleado;
import negocios.EmpleadoABM;

public class TestEliminarEmpleado {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EmpleadoABM abm = EmpleadoABM.getInstance();
		long idEmpleado=2;
		Empleado empleado=abm.traer(idEmpleado);
		System.out.printf("Empleado a eliminar: %s\n",empleado);
		
		try {
			abm.eliminar(idEmpleado);
			System.out.printf("Empleado eliminado\n");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
