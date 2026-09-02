package test;

import datos.UnidadVenta;
import negocios.UnidadVentaABM;

public class TestEliminarUnidadVenta {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UnidadVentaABM abm= new UnidadVentaABM();
		long idUnidadVenta=7;
		UnidadVenta unidadVenta=abm.traer(idUnidadVenta);
		System.out.printf("Unidad de venta a eliminar: %s\n",unidadVenta);
		
		try {
			abm.eliminar(idUnidadVenta);
			System.out.printf("Unidad de venta eliminada\n");
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
