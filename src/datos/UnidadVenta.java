package datos;

import java.util.Set;

public abstract class UnidadVenta {
	private long idUnidadVenta;
	private String nombreComercial;
	private Empleado responsable;
	private double superficie;
	private String codigo;
	private Set<Empleado> personal;
	private Set<Plato> platos;
	private Set<Pedido> pedidos;
	private double costosPorSuperficie;
	
	public UnidadVenta() {}
	
	public UnidadVenta(String nombreComercial, Empleado responsable, double superficie, String codigo) {
		super();
		this.nombreComercial = nombreComercial;
		this.responsable = responsable;
		this.superficie = superficie;
		this.codigo = codigo;
	}

	public long getIdUnidadVenta() {
		return idUnidadVenta;
	}
	protected void setIdUnidadVenta(long idUnidadVenta) {
		this.idUnidadVenta = idUnidadVenta;
	}
	
	public String getNombreComercial() {
		return nombreComercial;
	}

	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}

	public Empleado getResponsable() {
		return responsable;
	}

	public void setResponsable(Empleado responsable) {
		this.responsable = responsable;
	}

	public double getSuperficie() {
		return superficie;
	}

	public void setSuperficie(double superficie) {
		this.superficie = superficie;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public Set<Empleado> getPersonal(){
		return personal;
	}
	
	public void setPersonal(Set<Empleado> personal) {
		this.personal = personal;
	}
	
	public Set<Plato> getPlatos(){
		return platos;
	}
	
	public void setPlatos(Set<Plato> platos) {
		this.platos = platos;
	}

	public double getCostosPorSuperficie() {
		return costosPorSuperficie;
	}
	
	public void setCostosPorSuperficie(double costosPorSuperficie) {
		this.costosPorSuperficie=costosPorSuperficie;
	}
	
	public Set<Pedido> getPedidos(){
		return pedidos;
	}
	
	public void setPedidos(Set<Pedido> pedidos) {
		this.pedidos=pedidos;
	}
	
	@Override
	public String toString() {
		return "UnidadVenta [nombreComercial=" + nombreComercial + ", responsable=" + responsable + ", superficie="
				+ superficie + ", codigo=" + codigo + "]";
	}
	
	
}

