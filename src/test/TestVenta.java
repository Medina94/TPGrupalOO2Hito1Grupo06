package test;

import datos.Plato;
import datos.Venta;
import datos.Pedido;

import negocios.PlatoABM;
import negocios.VentaABM;
import negocios.PedidoABM;

public class TestVenta {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PlatoABM pAbm = new PlatoABM();
		VentaABM vAbm = new VentaABM();
		PedidoABM peAbm = new PedidoABM();
		
		long idPlato=1;
		long idPedido=1;
		
		Pedido pedido=peAbm.traer(idPedido);
		Plato plato=pAbm.traer(idPlato);
		
		
		try {
			Venta venta=new Venta(plato,4);
			venta.setPedido(pedido);
			long ultimoIdVenta=vAbm.agregar(venta);
			System.out.printf("Id venta: %d", ultimoIdVenta);
		}catch(Exception e) {
			e.printStackTrace();
			e.getLocalizedMessage();
		}
		
	}

}
