package datos;

<<<<<<< HEAD
public class FoodTruck extends UnidadVenta {
    private String patente;
    private boolean conexionElectrica;
    private double plusPorElectricidad;

    public FoodTruck() {
        super();
    }

    public FoodTruck(String nombreComercial, Empleado responsable, double superficie, String codigo,String patente,boolean conexionElectrica,Festival festival) {
		super(nombreComercial, responsable, superficie, codigo,festival);
		this.patente = patente;
		this.conexionElectrica = conexionElectrica;
	}
    
    public String getPatente() { 
    	return patente; 
    }
    public void setPatente(String patente) { 
    	this.patente = patente; 
    }

    public boolean isConexionElectrica() { 
    	return conexionElectrica; 
    }
    public void setConexionElectrica(boolean conexionElectrica) { 
    	this.conexionElectrica = conexionElectrica; 
    }

    public double getPlusPorElectricidad() { 
    	return plusPorElectricidad; 
    }
    public void setPlusPorElectricidad(double plusPorElectricidad) { 
    	this.plusPorElectricidad = plusPorElectricidad;
    }

	@Override
	public String toString() {
		return "FoodTruck\n["+ super.toString()+ "patente=" + patente + ", conexionElectrica=" + conexionElectrica + ", plusPorElectricidad="
				+ plusPorElectricidad + "]\n";
	}
    
    
}
=======
public class FoodTruck extends UnidadVenta{
	private String patente;
	boolean conexionElectrica;
	private double plusPorElectricidad;
	
	public FoodTruck() {}
	
	public FoodTruck(String nombreComercial, Empleado responsable, double superficie, String codigo,String patente,boolean conexionElectrica) {
		super(nombreComercial, responsable, superficie, codigo);
		this.patente = patente;
		this.conexionElectrica = conexionElectrica;
	}
	
	public String getPatente() {
		return patente;
	}
	public void setPatente(String patente) {
		this.patente = patente;
	}
	public boolean isConexionElectrica() {
		return conexionElectrica;
	}
	public void setConexionElectrica(boolean conexionElectrica) {
		this.conexionElectrica = conexionElectrica;
	}
	public double getPlusPorElectricidad(){
		return plusPorElectricidad;
	}
	public void setPlusPorElectricidad(double plusPorElectricidad) {
		this.plusPorElectricidad=plusPorElectricidad;
	}

	@Override
	public String toString() {
		return "FoodTruck [patente=" + patente + ", conexionElectrica=" + conexionElectrica + "]";
	}
	
	
}
>>>>>>> c8e297fe96c8bc76cef998c427cea5863f7bc759
