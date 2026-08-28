package datos;

import java.time.LocalDate;
import java.util.Set;

public class Festival {
	private long idFestival;
	private String nombre;
	private String temporada;
	private LocalDate inicio;
	private LocalDate fin;
	private Set<UnidadVenta> unidadesDeVenta;
	
	
	
	public Festival() {}
	
	public Festival(String nombre, String temporada, LocalDate inicio, LocalDate fin) {
		super();
		this.nombre = nombre;
		this.temporada = temporada;
		this.inicio = inicio;
		this.fin = fin;
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

	public LocalDate getInicio() {
		return inicio;
	}

	public void setInicio(LocalDate inicio) {
		this.inicio = inicio;
	}

	public LocalDate getFin() {
		return fin;
	}

	public void setFin(LocalDate fin) {
		this.fin = fin;
	}
	
	public Set<UnidadVenta> getUnidadesDeVenta() { 
		return unidadesDeVenta; 
	}
	public void setUnidadesDeVenta(Set<UnidadVenta> unidadesDeVenta) {
		this.unidadesDeVenta = unidadesDeVenta; 
	}
	
	@Override
	public String toString() {
		return "Festival [nombre=" + nombre + ", temporada=" + temporada + ", inicio=" + inicio + ", fin=" + fin + "]";
	}
	
	
	
	
}
