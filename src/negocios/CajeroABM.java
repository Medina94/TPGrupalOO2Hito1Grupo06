package negocios;


import java.util.List;

import dao.CajeroDao;
import datos.Cajero;

public class CajeroABM {
	private static CajeroABM instancia = null;
	
	protected CajeroABM() {}
	
	public static CajeroABM getInstance() {
		if(instancia==null)
		   instancia=new CajeroABM();
		return instancia;
	}
	
    public Cajero traer(int dni) {
        return CajeroDao.getInstance().traer(dni);
    }


    public void modificar(Cajero c) throws Exception {
        if (traer(c.getDni()) == null) {
            throw new Exception("ERROR: No existe un cajero con el DNI " + c.getDni());
        }
        CajeroDao.getInstance().actualizar(c);
    }

    public void eliminar(int dni) throws Exception {
        Cajero c = CajeroDao.getInstance().traer(dni);
        if (c == null) {
            throw new Exception("ERROR: El cajero no existe");
        }
        CajeroDao.getInstance().eliminar(c);
    }

    public List<Cajero> traer() {
        return CajeroDao.getInstance().traer();
    }
}

