package test;

import java.time.LocalDate;

import datos.Festival;
import negocios.FestivalABM;

public class TestTraerFestivalFecha {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FestivalABM festivalABM= FestivalABM.getInstance();
		
		System.out.println("AGREGAR FESTIVAL");
		try {
			long idFestival = festivalABM.agregar("Epicentro Lanus", "Invierno", LocalDate.of(2026, 6, 21), LocalDate.of(2026, 9, 21));
			System.out.printf("SE AGREGO EL FESTIVAL CON ID: %d ", idFestival);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
		System.out.println("AGREGAR FESTIVAL 2");
		try {
			long idFestival = festivalABM.agregar("Parque Lanus", "Verano", LocalDate.of(2026, 12, 21), LocalDate.of(2026, 3, 21));
			System.out.printf("SE AGREGO EL FESTIVAL CON ID: %d ", idFestival);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
		System.out.println("TRAER FESTIVAL POR FECHA");
		Festival festival=festivalABM.traer(LocalDate.of(2026, 12, 21));
		System.out.println(festival);
	}

}