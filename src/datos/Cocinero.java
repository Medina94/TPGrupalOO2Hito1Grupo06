package datos;

import java.time.LocalDate;

public class Cocinero extends Empleado {
    private String especialidadCulinaria;
    private double plus;

    public Cocinero() {
        super();
    }

<<<<<<< HEAD
    public Cocinero( String nombre, String apellido, int dni, LocalDate fechaNacimiento, LocalDate fechaIngreso, double sueldoBase, String especialidadCulinaria, double plus) {
        super( nombre, apellido, dni, fechaNacimiento, fechaIngreso, sueldoBase);
=======
    public Cocinero(int edad, String nombre, String apellido, int dni, LocalDate fechaNacimiento, LocalDate fechaIngreso, double sueldoBase, String especialidadCulinaria, double plus) {
        super(edad, nombre, apellido, dni, fechaNacimiento, fechaIngreso, sueldoBase);
>>>>>>> c8e297fe96c8bc76cef998c427cea5863f7bc759
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
<<<<<<< HEAD
		return "Cocinero\n["+ super.toString()+ "especialidadCulinaria=" + especialidadCulinaria + ", plus=" + plus + "]\n";
	}

	
	
=======
		return "Cocinero [especialidadCulinaria=" + especialidadCulinaria + ", plus=" + plus + ", getIdEmpleado()="
				+ getIdEmpleado() + ", getEdad()=" + getEdad() + ", getNombre()=" + getNombre() + ", getApellido()="
				+ getApellido() + ", getDni()=" + getDni() + ", getFechaNacimiento()=" + getFechaNacimiento()
				+ ", getFechaIngreso()=" + getFechaIngreso() + ", getSueldoBase()=" + getSueldoBase()
				+ ", getUnidadVenta()=" + getUnidadVenta() + ", toString()=" + super.toString() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + "]";
	}
>>>>>>> c8e297fe96c8bc76cef998c427cea5863f7bc759
    
    
}