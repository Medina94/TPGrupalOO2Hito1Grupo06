package test;

import negocios.FestivalABM;
import datos.Festival;

public class TestTraerFestivalYUnidadesDeVenta {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long idFestival=1;
		FestivalABM fAbm=new FestivalABM();
		
		Festival festival=fAbm.traerFestivalYUnidadesDeVenta(idFestival);
		System.out.printf("Traer Festival y Unidades de venta idFestival=%d\n",idFestival);
		System.out.printf("\n%s\n", festival);
		System.out.printf("\n%s", festival.getUnidadesDeVenta());
	}

}
