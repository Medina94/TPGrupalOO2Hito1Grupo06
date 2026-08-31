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

	protected void setIdVenta(long idVenta) {
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
		return "Venta [idVenta="+idVenta+"," + plato + ", cantidad=" + cantidad + "]\n";
	}
	
	
}
