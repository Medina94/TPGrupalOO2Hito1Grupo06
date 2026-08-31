package test;

import datos.Empleado;
import datos.Festival;

import negocios.FestivalABM;
import negocios.EmpleadoABM;
import negocios.UnidadVentaABM;

public class TestUnidadVenta {

	public static void main(String[] args) {
		
		FestivalABM fAbm= new FestivalABM();
		EmpleadoABM eAbm= new EmpleadoABM();
		
		long idFestival=1;
		Festival festival=fAbm.traer(idFestival);
		long idEmpleado = 3;
		Empleado empleado = eAbm.traer(idEmpleado);
		
		
		try {
			
			long ultimoIdPuestoDesarmable = UnidadVentaABM.getInstance().agregarPuestoDesarmable("grido", empleado, 200, "23212df214",2, 30,festival);
			long ultimoIdFoodTruck = UnidadVentaABM.getInstance().agregarFoodTruck("ugis",empleado,100,"23jd921312","qdr571",true,festival);
			
			System.out.printf("Id puesto desarmable: %d",ultimoIdPuestoDesarmable);
			System.out.printf("Id food truck: %d",ultimoIdFoodTruck);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
