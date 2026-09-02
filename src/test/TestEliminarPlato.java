package test;

import datos.Plato;
import negocios.PlatoABM;

public class TestEliminarPlato {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PlatoABM abm = new PlatoABM();
		long idPlato=6;
		Plato plato=abm.traer(idPlato);
		System.out.printf("Plato a eliminar: %s\n",plato);
		
		try {
			abm.eliminar(idPlato);
			System.out.printf("Plato eliminado\n");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
