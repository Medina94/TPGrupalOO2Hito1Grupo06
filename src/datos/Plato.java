package datos;

import java.util.Set;

public class Plato {
    private long idPlato;
    private String nombre;
    private double precioVenta;
    private double costoProduccion;
    private UnidadVenta unidadVenta;
    private Set<Venta> ventas;

    public Plato() {}

    public Plato(String nombre, double precioVenta, double costoProduccion, UnidadVenta unidadVenta) {
        this.nombre = nombre;
        this.precioVenta = precioVenta;
        this.costoProduccion = costoProduccion;
        this.unidadVenta= unidadVenta;
    }

    
    public long getIdPlato() { 
    	return idPlato; 
    }
    protected void setIdPlato(long idPlato) { 
    	this.idPlato = idPlato; 
    }

    public String getNombre() { 
    	return nombre; 
    }
    public void setNombre(String nombre) { 
    	this.nombre = nombre; 
    }

    public double getPrecioVenta() { 
    	return precioVenta; 
    }
    public void setPrecioVenta(double precioVenta) { 
    	this.precioVenta = precioVenta; 
    }

    public double getCostoProduccion() { 
    	return costoProduccion; 
    }
    public void setCostoProduccion(double costoProduccion) { 
    	this.costoProduccion = costoProduccion; 
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
		return "Plato [idPlato=" + idPlato + ", nombre=" + nombre + ", precioVenta=" + precioVenta
				+ ", costoProduccion=" + costoProduccion + "]\n";
	}
    
    
}