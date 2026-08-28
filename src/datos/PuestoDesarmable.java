package datos;

public class PuestoDesarmable extends UnidadVenta{

	private int cantidadCarpas;
	private int minutos;
	private double costosPorMontaje;
	
	public PuestoDesarmable(){}
	
	public PuestoDesarmable(String nombreComercial, Empleado responsable, double superficie, String codigo,
			int cantidadCarpas, int minutos) {
		super(nombreComercial, responsable, superficie, codigo);
		this.cantidadCarpas = cantidadCarpas;
		this.minutos = minutos;
	}

	public int getCantidadCarpas() {
		return cantidadCarpas;
	}
	public void setCantidadCarpas(int cantidadCarpas) {
		this.cantidadCarpas = cantidadCarpas;
	}
	public int getMinutos() {
		return minutos;
	}
	public void setMinutos(int minutos) {
		this.minutos = minutos;
	}
	public double getCostosPorMontaje() {
		return costosPorMontaje;
	}
	public void setCostosPorMontaje(double costosPorMontaje) {
		this.costosPorMontaje = costosPorMontaje;
	}
	
	@Override
	public String toString() {
		return "PuestoDesarmable [cantidadCarpas=" + cantidadCarpas + ", minutos=" + minutos + "]";
	}
	
	
}
