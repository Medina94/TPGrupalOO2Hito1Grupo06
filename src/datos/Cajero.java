package datos;

import java.time.LocalDate;

public class Cajero extends Empleado {
    private String turno;

    public Cajero() {
        super();
    }

    public Cajero(int edad, String nombre, String apellido, int dni, LocalDate fechaNacimiento, LocalDate fechaIngreso, double sueldoBase, String turno) {
        super(edad, nombre, apellido, dni, fechaNacimiento, fechaIngreso, sueldoBase);
        this.turno = turno;
    }

    public String getTurno() {
    	return turno; 
    }
    public void setTurno(String turno) { 
    	this.turno = turno; 
    }

	@Override
	public String toString() {
		return "Cajero [turno=" + turno + ", getIdEmpleado()=" + getIdEmpleado() + ", getEdad()=" + getEdad()
				+ ", getNombre()=" + getNombre() + ", getApellido()=" + getApellido() + ", getDni()=" + getDni()
				+ ", getFechaNacimiento()=" + getFechaNacimiento() + ", getFechaIngreso()=" + getFechaIngreso()
				+ ", getSueldoBase()=" + getSueldoBase() + ", getUnidadVenta()=" + getUnidadVenta() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}
    
    
}