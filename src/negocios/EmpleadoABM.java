package negocios;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import dao.EmpleadoDao;
import dao.UnidadVentaDao;
import datos.Empleado;
import datos.Cocinero;
import datos.Cajero;
import datos.UnidadVenta;

public class EmpleadoABM {
	private static EmpleadoABM instancia = null;
	
	protected EmpleadoABM() {}
	
	public static EmpleadoABM getInstance() {
		if(instancia==null)
		   instancia=new EmpleadoABM();
		return instancia;
	}
	
	public int agregarCocinero(String nombre, String apellido, int dni, LocalDate fechaNacimiento, LocalDate fechaIngreso, double sueldoBase, String especialidadCulinaria, double plus) throws Exception{
		if(calcularEdad(fechaNacimiento) < 18) {
    		throw new Exception("ERROR: El empleado a agregar no puede ser menor de edad.");
    	}
		if (EmpleadoDao.getInstance().traer(dni) != null) {
            throw new Exception("ERROR: Ya existe un empleado registrado con el DNI " + dni);
        }
		Cocinero cocinero=new Cocinero(nombre,apellido,dni,fechaNacimiento,fechaIngreso,sueldoBase,especialidadCulinaria,plus);
		cocinero.setAntiguedad(calcularAntiguedad(fechaIngreso));
		return EmpleadoDao.getInstance().agregar(cocinero);
	}
	
	public int agregarCajero(String nombre, String apellido, int dni, LocalDate fechaNacimiento, LocalDate fechaIngreso, double sueldoBase, String turno) throws Exception{
		if(calcularEdad(fechaNacimiento) < 18) {
    		throw new Exception("ERROR: El empleado a agregar no puede ser menor de edad.");
    	}
		if (EmpleadoDao.getInstance().traer(dni) != null) {
            throw new Exception("ERROR: Ya existe un empleado registrado con el DNI " + dni);
        }
		Cajero cajero= new Cajero(nombre,apellido,dni,fechaNacimiento,fechaIngreso,sueldoBase,turno);
		cajero.setAntiguedad(calcularAntiguedad(fechaIngreso));
		return EmpleadoDao.getInstance().agregar(cajero);
	}
	

    
    public void modificar(Empleado e) throws Exception{
    	Empleado empleado=EmpleadoDao.getInstance().traer(e.getDni());
    	if(empleado == null) {
    		throw new Exception("ERROR: El empleado no existe");
    	}
    	EmpleadoDao.getInstance().actualizar(e);
    }
    
    public void eliminar(long idEmpleado) throws Exception {
    	Empleado e=EmpleadoDao.getInstance().traer(idEmpleado);
    	if(e == null) {
    		throw new Exception("ERROR: El empleado no existe");
    	}
    	
    	UnidadVentaDao unidadVentaDao = UnidadVentaDao.getInstance();
        List<UnidadVenta> unidadesAsociadas = unidadVentaDao.traerPorResponsable(idEmpleado);
    	
    	if (unidadesAsociadas != null) {
            throw new Exception("ERROR: No se puede eliminar el empleado porque es responsable de una unidad de venta");
        }
    	
    	EmpleadoDao.getInstance().eliminar(e);
    }
    
    public Empleado traer(long idEmpleado) {
        return EmpleadoDao.getInstance().traer(idEmpleado);
    }

    public Empleado traer(int dni) {
        return EmpleadoDao.getInstance().traer(dni);
    }
    
    public int calcularAntiguedad(LocalDate fechaIngreso) {
    	int antiguedad = Period.between(fechaIngreso, LocalDate.now()).getYears();
    	return antiguedad;
    }
    
    public int calcularEdad(LocalDate fechaNacimiento) {
    	int edad=Period.between(fechaNacimiento, LocalDate.now()).getYears();
    	return edad;
    }
    
}