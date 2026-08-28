package negocios;

import java.util.List;
import dao.PuestoDesarmableDao;
import datos.PuestoDesarmable;

public class PuestoDesarmableABM {
    private PuestoDesarmableDao dao = new PuestoDesarmableDao();

    public PuestoDesarmable traer(String codigo) {
        return dao.traer(codigo);
    }


    public void modificar(PuestoDesarmable p) throws Exception {
        if (traer(p.getCodigo()) == null) {
            throw new Exception("ERROR: No existe un puesto con el codigo " + p.getCodigo());
        }
        dao.actualizar(p);
    }

    public void eliminar(String codigo) throws Exception {
        PuestoDesarmable p = dao.traer(codigo);
        if (p == null) {
            throw new Exception("ERROR: El puesto no existe");
        }
        dao.eliminar(p);
    }

    public List<PuestoDesarmable> traer() {
        return dao.traer();
    }
}