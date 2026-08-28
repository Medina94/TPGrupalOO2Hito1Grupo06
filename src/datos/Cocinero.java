package datos;

import java.time.LocalDate;

public class Cocinero extends Empleado {
    private String especialidadCulinaria;
    private double plus;

    public Cocinero() {
        super();
    }

    public Cocinero(int edad, String nombre, String apellido, int dni, LocalDate fechaNacimiento, LocalDate fechaIngreso, double sueldoBase, String especialidadCulinaria, double plus) {
        super(edad, nombre, apellido, dni, fechaNacimiento, fechaIngreso, sueldoBase);
        this.especialidadCulinaria = especialidadCulinaria;
        this.plus = plus;
    }

    public String getEspecialidadCulinaria() { 
    	return especialidadCulinaria; 
    }
    
    public void setEspecialidadCulinaria(String especialidadCulinaria) { 
    	this.especialidadCulinaria = especialidadCulinaria; 
    }

    public double getPlus() { 
    	return plus; 
    }
    
    public void setPlus(double plus) { 
    	this.plus = plus; 
    }

	@Override
	public String toString() {
		return "Cocinero [especialidadCulinaria=" + especialidadCulinaria + ", plus=" + plus + ", getIdEmpleado()="
				+ getIdEmpleado() + ", getEdad()=" + getEdad() + ", getNombre()=" + getNombre() + ", getApellido()="
				+ getApellido() + ", getDni()=" + getDni() + ", getFechaNacimiento()=" + getFechaNacimiento()
				+ ", getFechaIngreso()=" + getFechaIngreso() + ", getSueldoBase()=" + getSueldoBase()
				+ ", getUnidadVenta()=" + getUnidadVenta() + ", toString()=" + super.toString() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + "]";
	}
    
    
}