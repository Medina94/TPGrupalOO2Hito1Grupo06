package test;

import datos.Plato;
<<<<<<< HEAD
import datos.UnidadVenta;

import negocios.UnidadVentaABM;
=======
>>>>>>> c8e297fe96c8bc76cef998c427cea5863f7bc759
import negocios.PlatoABM;

public class TestPlato {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PlatoABM abm = new PlatoABM();
<<<<<<< HEAD
		UnidadVentaABM uAbm = new UnidadVentaABM();
		
		long idUnidadVenta=1;
		
		UnidadVenta unidadVenta=uAbm.traer(idUnidadVenta); 
		
		try{
			Plato plato = new Plato("pizza",6000.0,2000.0,unidadVenta);
=======
		
		try{
			Plato plato = new Plato("hamburguesa vegana",2500.0,1000.0);
>>>>>>> c8e297fe96c8bc76cef998c427cea5863f7bc759
			
			long ultimoIdPlato;
			ultimoIdPlato = abm.agregar(plato);
			System.out.printf("Id plato: %d", ultimoIdPlato);
		} catch (Exception e) {
<<<<<<< HEAD
			System.out.println(e.getMessage());
		}
		
		try{
			Plato plato = new Plato("papas fritas",5000.0,1000.0,unidadVenta);
			
			long ultimoIdPlato;
			ultimoIdPlato = abm.agregar(plato);
			System.out.printf("Id plato: %d", ultimoIdPlato);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		try{
			Plato plato = new Plato("choripan",2000.0,500.0,unidadVenta);
			
			long ultimoIdPlato;
			ultimoIdPlato = abm.agregar(plato);
			System.out.printf("Id plato: %d", ultimoIdPlato);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		try{
			Plato plato = new Plato("milanesa napolitana",6000.0,1000.0,unidadVenta);
			
			long ultimoIdPlato;
			ultimoIdPlato = abm.agregar(plato);
			System.out.printf("Id plato: %d", ultimoIdPlato);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		try{
			Plato plato = new Plato("empanadas",3000.0,1500.0,unidadVenta);
			
			long ultimoIdPlato;
			ultimoIdPlato = abm.agregar(plato);
			System.out.printf("Id plato: %d", ultimoIdPlato);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		try{
			Plato plato = new Plato("hamburguesa con queso",6000.0,2000.0,unidadVenta);
			
			long ultimoIdPlato;
			ultimoIdPlato = abm.agregar(plato);
			System.out.printf("Id plato: %d", ultimoIdPlato);
		} catch (Exception e) {
			System.out.println(e.getMessage());
=======
			e.getMessage();
>>>>>>> c8e297fe96c8bc76cef998c427cea5863f7bc759
		}
	}

}
