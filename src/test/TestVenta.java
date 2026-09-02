package test;

import datos.Pedido;
import datos.Plato;
import datos.Venta;
import negocios.PedidoABM;
import negocios.PlatoABM;
import negocios.VentaABM;

public class TestVenta {

	public static void main(String[] args) {

		PlatoABM pAbm = PlatoABM.getInstance();
		VentaABM vAbm = VentaABM.getInstance();
		PedidoABM peAbm = PedidoABM.getInstance();
		
		long idPedido=2;
		
		
		try {
			long idPlato=2;
			
			Pedido pedido=peAbm.traer(idPedido);
			Plato plato=pAbm.traer(idPlato);
			
			Venta venta=new Venta(plato,2);
			venta.setPedido(pedido);
			
			long ultimoIdVenta=vAbm.agregar(venta);
			
			System.out.printf("Id venta: %d", ultimoIdVenta);
		}catch(Exception e) {
			e.getLocalizedMessage();
		}
		
		try {
			long idPlato=3;
			
			Pedido pedido=peAbm.traer(idPedido);
			Plato plato=pAbm.traer(idPlato);
			
			Venta venta=new Venta(plato,1);
			venta.setPedido(pedido);
			
			long ultimoIdVenta=vAbm.agregar(venta);
			
			System.out.printf("Id venta: %d", ultimoIdVenta);
		}catch(Exception e) {
			e.getLocalizedMessage();
		}
		
		try {
			long idPlato=5;
			
			Pedido pedido=peAbm.traer(idPedido);
			Plato plato=pAbm.traer(idPlato);
			
			Venta venta=new Venta(plato,2);
			venta.setPedido(pedido);
			
			long ultimoIdVenta=vAbm.agregar(venta);
			
			System.out.printf("Id venta: %d", ultimoIdVenta);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

}
