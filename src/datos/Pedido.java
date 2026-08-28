package datos;

import java.time.LocalDate;
import java.util.Set;

public class Pedido {
	private long idPedido;
	private LocalDate fecha;
	private Festival festival;
	private UnidadVenta unidadVenta;
	private Set<Venta> ventas;
	
	public Pedido(){}
	
	public Pedido(LocalDate fecha, Festival festival,UnidadVenta unidadVenta) {
		super();
		this.fecha = fecha;
		this.festival = festival;
		this.unidadVenta=unidadVenta;
	}

	public long getIdPedido() {
		return idPedido;
	}
	
	protected void setIdPedido(long idPedido) {
		this.idPedido = idPedido;
	}
	
	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Festival getFestival() {
		return festival;
	}

	public void setFestival(Festival festival) {
		this.festival = festival;
	}

	public UnidadVenta getUnidadVenta() {
		return unidadVenta;
	}
	
	public void setUnidadVenta(UnidadVenta unidadVenta) {
		this.unidadVenta = unidadVenta;
	}

	public Set<Venta> getVentas() {
		return ventas;
	}

	public void setVentas(Set<Venta> ventas) {
		this.ventas = ventas;
	}

	@Override
	public String toString() {
		return "Pedido [fecha=" + fecha + ", festival=" + festival + ", unidadVenta=" + unidadVenta +  "]";
	}

	
	
	
	
	
}
