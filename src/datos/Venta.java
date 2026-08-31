package datos;

public class Venta {
	private long idVenta;
	private Plato plato;
	private int cantidad;
	private Pedido pedido;
	
	public Venta() {}
	
	public Venta(Plato plato, int cantidad) {
		super();
	
		this.plato = plato;
		this.cantidad = cantidad;
	}
	
	public long getIdVenta() {
		return idVenta;
	}

<<<<<<< HEAD
	protected void setIdVenta(long idVenta) {
=======
	public void setIdVenta(long idVenta) {
>>>>>>> c8e297fe96c8bc76cef998c427cea5863f7bc759
	    this.idVenta = idVenta;
	}
	
	public Plato getPlato() {
		return plato;
	}
	public void setPlato(Plato plato) {
		this.plato = plato;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido=pedido;
	}
	@Override
	
	public String toString() {
<<<<<<< HEAD
		return "Venta [idVenta="+idVenta+"," + plato + ", cantidad=" + cantidad + "]\n";
=======
		return "Venta [plato=" + plato + ", cantidad=" + cantidad + "]";
>>>>>>> c8e297fe96c8bc76cef998c427cea5863f7bc759
	}
	
	
}
