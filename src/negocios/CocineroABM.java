package negocios;

import java.util.List;

import dao.CocineroDao;
import datos.Cocinero;

public class CocineroABM {
	private static CocineroABM instancia = null;
	
	protected CocineroABM() {}
	
	public static CocineroABM getInstance() {
		if(instancia==null)
		   instancia=new CocineroABM();
		return instancia;
	}
	
    public Cocinero traer(int dni) {
        return CocineroDao.getInstance().traer(dni);
    }

    public void modificar(Cocinero c) throws Exception {
        if (traer(c.getDni()) == null) {
            throw new Exception("ERROR: No existe un cocinero con el DNI " + c.getDni());
        }
        CocineroDao.getInstance().actualizar(c);
    }

    public void eliminar(int dni) throws Exception {
        Cocinero c = CocineroDao.getInstance().traer(dni);
        if (c == null) {
            throw new Exception("ERROR: El cocinero no existe");
        }
        CocineroDao.getInstance().eliminar(c);
    }

    public List<Cocinero> traer() {
        return CocineroDao.getInstance().traer();
    }
}