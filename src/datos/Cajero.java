package datos;

import java.time.LocalDate;

public class Cajero extends Empleado {
    private String turno;

    public Cajero() {
        super();
    }

<<<<<<< HEAD
    public Cajero( String nombre, String apellido, int dni, LocalDate fechaNacimiento, LocalDate fechaIngreso, double sueldoBase, String turno) {
        super( nombre, apellido, dni, fechaNacimiento, fechaIngreso, sueldoBase);
=======
    public Cajero(int edad, String nombre, String apellido, int dni, LocalDate fechaNacimiento, LocalDate fechaIngreso, double sueldoBase, String turno) {
        super(edad, nombre, apellido, dni, fechaNacimiento, fechaIngreso, sueldoBase);
>>>>>>> c8e297fe96c8bc76cef998c427cea5863f7bc759
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
<<<<<<< HEAD
		return "Cajero\n["+ super.toString()+ "turno=" + turno + "]\n";
	}

	
=======
		return "Cajero [turno=" + turno + ", getIdEmpleado()=" + getIdEmpleado() + ", getEdad()=" + getEdad()
				+ ", getNombre()=" + getNombre() + ", getApellido()=" + getApellido() + ", getDni()=" + getDni()
				+ ", getFechaNacimiento()=" + getFechaNacimiento() + ", getFechaIngreso()=" + getFechaIngreso()
				+ ", getSueldoBase()=" + getSueldoBase() + ", getUnidadVenta()=" + getUnidadVenta() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}
>>>>>>> c8e297fe96c8bc76cef998c427cea5863f7bc759
    
    
}