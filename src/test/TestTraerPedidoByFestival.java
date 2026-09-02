package test;

import java.util.List;

import datos.Pedido;
import negocios.PedidoABM;

public class TestTraerPedidoByFestival {

	public static void main(String[] args) {
		// TRAER PEDIDOS POR ID FESTIVAL
		List<Pedido> pedidos = PedidoABM.getInstance().traerByFestival(1L);
		
		try {
			pedidos = PedidoABM.getInstance().traerByFestival(1L);
			mostrarListaPedidos(pedidos);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("********************************************************");
		System.out.println("********************************************************");
		
		// TRAER PEDIDOS POR ID FESTIVAL y FECHA
		List<Pedido> pedidosByFecha;
		try {
			pedidosByFecha = PedidoABM.getInstance().traerByFestival(1L, "2026-09-02");
			mostrarListaPedidos(pedidosByFecha);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static void mostrarListaPedidos(List<Pedido> pedidos) {
		for(Pedido p : pedidos) {
			System.out.println(p.toString());
		}
	}

}
