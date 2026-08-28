package test;


import datos.PuestoDesarmable;
import datos.FoodTruck;
import datos.Empleado;

import negocios.EmpleadoABM;
import negocios.UnidadVentaABM;

public class TestUnidadVenta {

	public static void main(String[] args) {
		EmpleadoABM eAbm= new EmpleadoABM();
		UnidadVentaABM uAbm = new UnidadVentaABM();
		
		long idEmpleado = 3;
		Empleado empleado = eAbm.traer(idEmpleado);
		
		
		try {
			PuestoDesarmable puestoDesarmable = new PuestoDesarmable("tacos", empleado, 200, "23as23fg34",2, 30);
			
			FoodTruck foodTruck = new FoodTruck("milanesas",empleado,100,"23jd903e02","hqz231",true);
			
			long ultimoIdPuestoDesarmable = uAbm.agregar(puestoDesarmable);
			System.out.printf("Id puesto desarmable: %d",ultimoIdPuestoDesarmable);
			
			long ultimoIdFoodTruck = uAbm.agregar(foodTruck);
			System.out.printf("Id food truck: %d",ultimoIdFoodTruck);
			
		}catch(Exception i) {
			i.getMessage();
		}
	}

}
