package test;

import datos.Empleado;
import datos.UnidadVenta;

import negocios.EmpleadoABM;
import negocios.UnidadVentaABM;

public class TestAgregarEmpleados {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EmpleadoABM eAbm = new EmpleadoABM();
		UnidadVentaABM uAbm = new UnidadVentaABM();
		
		try {
			long idEmpleado=3;
			long idUnidadVenta=2;
			
			Empleado empleado=eAbm.traer(idEmpleado);
			UnidadVenta unidadVenta=uAbm.traer(idUnidadVenta);
			
			empleado.setUnidadVenta(unidadVenta);
			
			eAbm.modificar(empleado);
			
			System.out.println(empleado);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		try {
			long idEmpleado=4;
			long idUnidadVenta=2;
			
			Empleado empleado=eAbm.traer(idEmpleado);
			UnidadVenta unidadVenta=uAbm.traer(idUnidadVenta);
			
			empleado.setUnidadVenta(unidadVenta);
			
			eAbm.modificar(empleado);
			
			System.out.println(empleado);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
