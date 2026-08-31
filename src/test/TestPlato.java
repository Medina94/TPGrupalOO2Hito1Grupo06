package test;

import datos.Plato;
import datos.UnidadVenta;

import negocios.UnidadVentaABM;
import negocios.PlatoABM;

public class TestPlato {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PlatoABM abm = new PlatoABM();
		UnidadVentaABM uAbm = new UnidadVentaABM();
		
		long idUnidadVenta=1;
		
		UnidadVenta unidadVenta=uAbm.traer(idUnidadVenta); 
		
		try{
			Plato plato = new Plato("pizza",6000.0,2000.0,unidadVenta);
			
			long ultimoIdPlato;
			ultimoIdPlato = abm.agregar(plato);
			System.out.printf("Id plato: %d", ultimoIdPlato);
		} catch (Exception e) {
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
		}
	}

}
