package test;

import negocios.UnidadVentaABM;
import datos.UnidadVenta;

public class TestTraerUnidadVentaYPlatos {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long idUnidadVenta=1;
		UnidadVentaABM uAbm=new UnidadVentaABM();
		
		UnidadVenta unidadVenta=uAbm.traerUnidadVentaYPlatos(idUnidadVenta);
		System.out.printf("Traer Unidad de Venta y Platos idUnidadVenta=%d\n",idUnidadVenta);
		System.out.printf("\n%s\n", unidadVenta);
		System.out.printf("\n%s\n", unidadVenta.getPlatos());
	}

}
