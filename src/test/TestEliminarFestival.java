package test;

import datos.Festival;
import negocios.FestivalABM;

public class TestEliminarFestival {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FestivalABM abm = FestivalABM.getInstance();
		long idFestival=1;
		Festival festival=abm.traer(idFestival);
		System.out.printf("Festival a eliminar: %s\n", festival);
		
		try {
			
			abm.eliminar(idFestival);
			System.out.printf("Festival eliminado\n");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		

	}

}
