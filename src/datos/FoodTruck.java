package datos;

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
