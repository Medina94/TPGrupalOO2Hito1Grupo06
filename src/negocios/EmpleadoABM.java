package negocios;

import dao.EmpleadoDao;
import datos.Empleado;

public class EmpleadoABM {
    private EmpleadoDao dao = new EmpleadoDao();

    public Empleado traer(long idEmpleado) {
        return dao.traer(idEmpleado);
    }

    public Empleado traer(int dni) {
        return dao.traer(dni);
    }

    public long agregar(Empleado e) throws Exception {
    
        if (e == null) {
            throw new Exception("ERROR: El empleado a agregar no puede ser nulo.");
        }

        if (dao.traer(e.getDni()) != null) {
            throw new Exception("ERROR: Ya existe un empleado registrado con el DNI " + e.getDni());
        }

        return dao.agregar(e);
    }
}