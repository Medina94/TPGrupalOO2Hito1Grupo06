package datos;
import java.time.LocalDate;

public abstract class Empleado {
	private long idEmpleado;
	private String nombre;
	private String apellido;
	private int dni;
	private LocalDate fechaNacimiento;
	private LocalDate fechaIngreso;
	private double sueldoBase;
	private UnidadVenta unidadVenta;
	private int antiguedad;
	
	public Empleado() {}
	
	public Empleado( String nombre, String apellido, int dni, LocalDate fechaNacimiento,
			LocalDate fechaIngreso, double sueldoBase) {
		super();
		
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.fechaNacimiento = fechaNacimiento;
		this.fechaIngreso = fechaIngreso;
		this.sueldoBase = sueldoBase;
		
	}
	
	public long getIdEmpleado() {
		return idEmpleado;
	}

	protected void setIdEmpleado(long idEmpleado) {
	    this.idEmpleado = idEmpleado;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public int getDni() {
		return dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public double getSueldoBase() {
		return sueldoBase;
	}
	public void setSueldoBase(double sueldoBase) {
		this.sueldoBase = sueldoBase;
	}
	
	public UnidadVenta getUnidadVenta() {
		return unidadVenta;
	}
	public void setUnidadVenta(UnidadVenta unidadVenta) {
		this.unidadVenta=unidadVenta;
	}
	
	public int getAntiguedad() {
		return antiguedad;
	}
	public void setAntiguedad(int antiguedad) {
		this.antiguedad=antiguedad;
	}
	
	@Override
	public String toString() {
		return "Empleado [idEmpleado="+idEmpleado+", nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni
				+ ", fechaNacimiento=" + fechaNacimiento + ", fechaIngreso=" + fechaIngreso + ", sueldoBase="
				+ sueldoBase + ", antiguedad=" + antiguedad + "]";
	}
	
	
}

