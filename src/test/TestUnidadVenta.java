package test;

import datos.Empleado;
import datos.Festival;
import negocios.EmpleadoABM;
import negocios.FestivalABM;
import negocios.UnidadVentaABM;

public class TestUnidadVenta {

	public static void main(String[] args) {
		
		FestivalABM fAbm= FestivalABM.getInstance();
		EmpleadoABM eAbm= EmpleadoABM.getInstance();
		
		long idFestival=1;
		Festival festival=fAbm.traer(idFestival);
		long idEmpleado = 1;
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
