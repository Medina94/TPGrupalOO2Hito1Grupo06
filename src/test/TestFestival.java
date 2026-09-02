package test;

import java.time.LocalDate;

import negocios.FestivalABM;
public class TestFestival {

	public static void main(String[] args) {
		FestivalABM abm = FestivalABM.getInstance();

		try {
			long ultimoIdFestival;
			ultimoIdFestival = abm.agregar("epicentro lanus", "invierno", LocalDate.now(), LocalDate.of(2026,9,23));
			System.out.printf("Id Festival: %d", ultimoIdFestival);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
