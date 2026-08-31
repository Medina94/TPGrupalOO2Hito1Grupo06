package test;

import negocios.UnidadVentaABM;
import datos.UnidadVenta;

public class TestTraerUnidadVentaYEmpleados {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long idUnidadVenta=10;
		UnidadVentaABM uAbm=new UnidadVentaABM();
		
		UnidadVenta unidadVenta=uAbm.traerUnidadVentaYEmpleados(idUnidadVenta);
		System.out.printf("Traer Unidad de Venta y Empleados idUnidadVenta=%d\n",idUnidadVenta);
		System.out.printf("\n%s\n",unidadVenta);
		System.out.printf("\n%s\n", unidadVenta.getEmpleados());
	}

}
