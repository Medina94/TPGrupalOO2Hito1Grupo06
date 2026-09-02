package test;

import java.time.LocalDate;
import java.util.List;

import datos.Festival;
import negocios.FestivalABM;

public class TestTraerFestivalFecha {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FestivalABM festivalABM=new FestivalABM();
		
		System.out.println("AGREGAR FESTIVAL");
		try {
			long idFestival = festivalABM.agregar("Epicentro Lanus", "invierno", LocalDate.now(), LocalDate.of(2026,9,23));
			System.out.printf("SE AGREGO EL FESTIVAL CON ID: %d ", idFestival);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
		System.out.println("TRAER FESTIVAL POR FECHA");
		Festival festival=festivalABM.traer(LocalDate.of(2026, 9, 2));
		System.out.println(festival);
	}

}
