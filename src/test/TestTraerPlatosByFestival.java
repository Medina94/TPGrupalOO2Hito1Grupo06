package test;

import java.util.List;

import datos.Plato;
import negocios.PlatoABM;

public class TestTraerPlatosByFestival {

	public static void main(String[] args) {
		PlatoABM platoABM = PlatoABM.getInstance();
		try {
			System.out.println("Consultar listado de platos por festival");
			List<Plato> platos = platoABM.traerByFestival(1L);
			mostrarListaPlatos(platos);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private static final void mostrarListaPlatos(List<Plato> lista) {
		for(Plato p : lista) {
			System.out.println(p.toString());
		}
	}
	
}
