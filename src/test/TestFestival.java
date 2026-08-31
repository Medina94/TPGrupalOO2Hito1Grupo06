package test;

import java.time.LocalDate;
import negocios.FestivalABM;
public class TestFestival {

	public static void main(String[] args) {
		FestivalABM abm = new FestivalABM();

		try {
			long ultimoIdFestival;
			ultimoIdFestival = abm.agregar("epicentro lanus", "invierno", LocalDate.now(), LocalDate.of(2026,9,23));
			System.out.printf("Id Festival: %d", ultimoIdFestival);
		} catch (Exception e) {
<<<<<<< HEAD
			System.out.println(e.getMessage());
=======
			e.getMessage();
>>>>>>> c8e297fe96c8bc76cef998c427cea5863f7bc759
		}
	}

}
