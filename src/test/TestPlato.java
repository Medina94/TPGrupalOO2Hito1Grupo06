package test;

import datos.Plato;
import negocios.PlatoABM;

public class TestPlato {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PlatoABM abm = new PlatoABM();
		
		try{
			Plato plato = new Plato("hamburguesa vegana",2500.0,1000.0);
			
			long ultimoIdPlato;
			ultimoIdPlato = abm.agregar(plato);
			System.out.printf("Id plato: %d", ultimoIdPlato);
		} catch (Exception e) {
			e.getMessage();
		}
	}

}
