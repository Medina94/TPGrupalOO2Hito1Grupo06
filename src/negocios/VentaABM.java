package negocios;

import java.util.List;
import dao.VentaDao;
import datos.Venta;
import datos.Pedido;

public class VentaABM {
    private VentaDao dao = new VentaDao();

    public Venta traer(long id) {
        return dao.traer(id);
    }

    public int agregar(Venta v) throws Exception {
        return dao.agregar(v);
    }

    public void modificar(Venta v) throws Exception {
        if (traer(v.getIdVenta()) == null) {
            throw new Exception("ERROR: No existe venta con ID " + v.getIdVenta());
        }
        dao.actualizar(v);
    }

    public void eliminar(long id) throws Exception {
        Venta v = dao.traer(id);
        if (v == null) {
            throw new Exception("ERROR: La venta no existe");
        }
        dao.eliminar(v);
    }

    public List<Venta> traer(Pedido p) {
        return dao.traer(p);
    }
}