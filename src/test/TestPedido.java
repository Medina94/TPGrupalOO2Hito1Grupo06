package test;

<<<<<<< HEAD
=======

import java.time.LocalDate;

>>>>>>> c8e297fe96c8bc76cef998c427cea5863f7bc759
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
<<<<<<< HEAD
			Pedido pedido= new Pedido(festival,unidadVenta);
=======
			Pedido pedido= new Pedido(LocalDate.of(2026, 8, 24),festival,unidadVenta);
>>>>>>> c8e297fe96c8bc76cef998c427cea5863f7bc759
			
			long ultimoIdPedido=pAbm.agregar(pedido);
			System.out.printf("Id pedido: %d",ultimoIdPedido);
		} catch(Exception e) {
<<<<<<< HEAD
			System.out.println(e.getMessage());
=======
			e.getLocalizedMessage();
>>>>>>> c8e297fe96c8bc76cef998c427cea5863f7bc759
		}

	}

}
