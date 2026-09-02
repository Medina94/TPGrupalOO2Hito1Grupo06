package test;

import negocios.UnidadVentaABM;
import datos.UnidadVenta;
import datos.Pedido;

public class TestTraerUnidadVentaYPedidos {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long idUnidadVenta=1;
		UnidadVentaABM uAbm=new UnidadVentaABM();
		
		UnidadVenta unidadVenta=uAbm.traerUnidadVentaYPedidos(idUnidadVenta);
		System.out.printf("Traer Unidad de Venta y Pedidos idUnidadVenta=%d\n",idUnidadVenta);
		System.out.printf("\n%s\n", unidadVenta);
		
		for (Pedido p : unidadVenta.getPedidos()) {
		    System.out.println("Pedido [idPedido=" + p.getIdPedido() + ", fecha=" + p.getFecha() + "]");
		    System.out.printf("\n%s\n", p.getVentas());
		}

	}

}
