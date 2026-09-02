package test;


import datos.Pedido;
import negocios.PedidoABM;

public class TestTraerPedidoYVentas {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long idPedido=1;
		PedidoABM pAbm= PedidoABM.getInstance();

		
		Pedido pedido=pAbm.traerPedidoYVentas(idPedido);
		System.out.printf("Traer Pedido y Ventas idPedido=%d\n",idPedido);
		System.out.printf("\n%s\n", pedido);
		System.out.printf("\n%s\n", pedido.getVentas());
		
	}

}
