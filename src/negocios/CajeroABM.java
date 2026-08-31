package negocios;


import java.util.List;
import dao.CajeroDao;
import datos.Cajero;

public class CajeroABM {
    private CajeroDao dao = new CajeroDao();

    public Cajero traer(int dni) {
        return dao.traer(dni);
    }


    public void modificar(Cajero c) throws Exception {
        if (traer(c.getDni()) == null) {
            throw new Exception("ERROR: No existe un cajero con el DNI " + c.getDni());
        }
        dao.actualizar(c);
    }

    public void eliminar(int dni) throws Exception {
        Cajero c = dao.traer(dni);
        if (c == null) {
            throw new Exception("ERROR: El cajero no existe");
        }
        dao.eliminar(c);
    }

    public List<Cajero> traer() {
        return dao.traer();
    }
}

