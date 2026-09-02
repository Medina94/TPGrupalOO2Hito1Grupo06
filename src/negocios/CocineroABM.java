package negocios;

import java.util.List;
import dao.CocineroDao;
import datos.Cocinero;

public class CocineroABM {
    private CocineroDao dao = new CocineroDao();

    public Cocinero traer(int dni) {
        return dao.traer(dni);
    }

    public void modificar(Cocinero c) throws Exception {
        if (traer(c.getDni()) == null) {
            throw new Exception("ERROR: No existe un cocinero con el DNI " + c.getDni());
        }
        dao.actualizar(c);
    }

    public void eliminar(int dni) throws Exception {
        Cocinero c = dao.traer(dni);
        if (c == null) {
            throw new Exception("ERROR: El cocinero no existe");
        }
        dao.eliminar(c);
    }

    public List<Cocinero> traer() {
        return dao.traer();
    }
}