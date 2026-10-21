package datos;

import java.time.LocalDate;
import java.util.Set;

public class Festival {
	private long idFestival;
	private String nombre;
	private String temporada;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	private Set<UnidadVenta> unidadesDeVenta;
	private Set<Pedido> pedidos;
	
	
	public Festival() {}
	
	public Festival(String nombre, String temporada, LocalDate fechaInicio, LocalDate fechaFin) {
		super();
		this.nombre = nombre;
		this.temporada = temporada;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}
	public long getIdFestival() {
		return idFestival;
	}
	
	protected void setIdFestival(long idFestival) {
		this.idFestival = idFestival;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTemporada() {
		return temporada;
	}

	public void setTemporada(String temporada) {
		this.temporada = temporada;
	}

	public LocalDate getFechaInicio() { 
        return fechaInicio; 
    }
    public void setFechaInicio(LocalDate fechaInicio) { 
        this.fechaInicio = fechaInicio; 
    }

    public LocalDate getFechaFin() { 
    	return fechaFin; 
    }
    public void setFechaFin(LocalDate fechaFin) { 
    	this.fechaFin = fechaFin; 
    }
    
	public Set<UnidadVenta> getUnidadesDeVenta() { 
		return unidadesDeVenta; 
	}
	public void setUnidadesDeVenta(Set<UnidadVenta> unidadesDeVenta) {
		this.unidadesDeVenta = unidadesDeVenta; 
	}
	
	public Set<Pedido> getPedidos() { 
        return pedidos; 
    }
    
    public void setPedidos(Set<Pedido> pedidos) { 
        this.pedidos = pedidos; 
    }
	
	@Override
	public String toString() {
		return "Festival [nombre=" + nombre + ", temporada=" + temporada + ", inicio=" + fechaInicio + ", fin=" + fechaFin + "\n";
	}
	
	
	
	
}
