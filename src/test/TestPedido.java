package test;

import datos.Festival;
import datos.Pedido;
import datos.UnidadVenta;

import negocios.FestivalABM;
import negocios.UnidadVentaABM;
import negocios.PedidoABM;

public class TestPedido {

	public static void main(String[] args) {
		PedidoABM pAbm = new PedidoABM();
		FestivalABM fAbm = new FestivalABM();
		UnidadVentaABM uAbm = new UnidadVentaABM();
		
		long idUnidadVenta= 1;
		
		Festival festival = fAbm.traer("epicentro lanus");
		UnidadVenta unidadVenta = uAbm.traer(idUnidadVenta);
		
		try {
			Pedido pedido= new Pedido(festival,unidadVenta);
			
			long ultimoIdPedido=pAbm.agregar(pedido);
			System.out.printf("Id pedido: %d",ultimoIdPedido);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
