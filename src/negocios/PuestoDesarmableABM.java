package negocios;

import java.util.List;

import dao.PuestoDesarmableDao;
import datos.PuestoDesarmable;

public class PuestoDesarmableABM {
	private static PuestoDesarmableABM instancia = null;
	
	protected PuestoDesarmableABM() {}
	
	public static PuestoDesarmableABM getInstance() {
		if(instancia==null)
		   instancia=new PuestoDesarmableABM();
		return instancia;
	}
	
    public void modificar(PuestoDesarmable p) throws Exception {
        if (traer(p.getCodigo()) == null) {
            throw new Exception("ERROR: No existe un puesto con el codigo " + p.getCodigo());
        }
        PuestoDesarmableDao.getInstance().actualizar(p);
    }

    public void eliminar(String codigo) throws Exception {
        PuestoDesarmable p = PuestoDesarmableDao.getInstance().traer(codigo);
        if (p == null) {
            throw new Exception("ERROR: El puesto no existe");
        }
        PuestoDesarmableDao.getInstance().eliminar(p);
    }
    
    public PuestoDesarmable traer(String codigo) {
        return PuestoDesarmableDao.getInstance().traer(codigo);
    }

    public List<PuestoDesarmable> traer() {
        return PuestoDesarmableDao.getInstance().traer();
    }
}