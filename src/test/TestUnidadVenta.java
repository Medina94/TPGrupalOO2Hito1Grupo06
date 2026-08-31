package test;

<<<<<<< HEAD
import datos.Empleado;
import datos.Festival;

import negocios.FestivalABM;
=======

import datos.PuestoDesarmable;
import datos.FoodTruck;
import datos.Empleado;

>>>>>>> c8e297fe96c8bc76cef998c427cea5863f7bc759
import negocios.EmpleadoABM;
import negocios.UnidadVentaABM;

public class TestUnidadVenta {

	public static void main(String[] args) {
<<<<<<< HEAD
		
		FestivalABM fAbm= new FestivalABM();
		EmpleadoABM eAbm= new EmpleadoABM();
		
		long idFestival=1;
		Festival festival=fAbm.traer(idFestival);
=======
		EmpleadoABM eAbm= new EmpleadoABM();
		UnidadVentaABM uAbm = new UnidadVentaABM();
		
>>>>>>> c8e297fe96c8bc76cef998c427cea5863f7bc759
		long idEmpleado = 3;
		Empleado empleado = eAbm.traer(idEmpleado);
		
		
		try {
<<<<<<< HEAD
			
			long ultimoIdPuestoDesarmable = UnidadVentaABM.getInstance().agregarPuestoDesarmable("grido", empleado, 200, "23212df214",2, 30,festival);
			long ultimoIdFoodTruck = UnidadVentaABM.getInstance().agregarFoodTruck("ugis",empleado,100,"23jd921312","qdr571",true,festival);
			
			System.out.printf("Id puesto desarmable: %d",ultimoIdPuestoDesarmable);
			System.out.printf("Id food truck: %d",ultimoIdFoodTruck);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
=======
			PuestoDesarmable puestoDesarmable = new PuestoDesarmable("tacos", empleado, 200, "23as23fg34",2, 30);
			
			FoodTruck foodTruck = new FoodTruck("milanesas",empleado,100,"23jd903e02","hqz231",true);
			
			long ultimoIdPuestoDesarmable = uAbm.agregar(puestoDesarmable);
			System.out.printf("Id puesto desarmable: %d",ultimoIdPuestoDesarmable);
			
			long ultimoIdFoodTruck = uAbm.agregar(foodTruck);
			System.out.printf("Id food truck: %d",ultimoIdFoodTruck);
			
		}catch(Exception i) {
			i.getMessage();
>>>>>>> c8e297fe96c8bc76cef998c427cea5863f7bc759
		}
	}

}
