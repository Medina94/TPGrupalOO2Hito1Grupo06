package test;

import datos.Festival;
import datos.Pedido;
import datos.UnidadVenta;
import negocios.FestivalABM;
import negocios.PedidoABM;
import negocios.UnidadVentaABM;

public class TestPedido {

	public static void main(String[] args) {
		PedidoABM pAbm = PedidoABM.getInstance();
		FestivalABM fAbm = FestivalABM.getInstance();
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
