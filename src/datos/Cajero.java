package datos;

import java.time.LocalDate;

public class Cajero extends Empleado {
    private String turno;

    public Cajero() {
        super();
    }

    public Cajero( String nombre, String apellido, int dni, LocalDate fechaNacimiento, LocalDate fechaIngreso, double sueldoBase, String turno) {
        super( nombre, apellido, dni, fechaNacimiento, fechaIngreso, sueldoBase);
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
		return "Cajero\n["+ super.toString()+ "turno=" + turno + "]\n";
	}

	
    
    
}