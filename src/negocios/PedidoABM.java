package negocios;

import java.util.List;
import dao.PedidoDao;
import datos.Pedido;
import datos.UnidadVenta;

public class PedidoABM {
    private PedidoDao dao = new PedidoDao();

    public Pedido traer(long idPedido) {
        return dao.traer(idPedido);
    }

    public int agregar(Pedido p) throws Exception {
  
        return dao.agregar(p);
    }

    public void modificar(Pedido p) throws Exception {
        if (traer(p.getIdPedido()) == null) {
            throw new Exception("ERROR: No existe el pedido con ID " + p.getIdPedido());
        }
        dao.actualizar(p);
    }

    public void eliminar(long idPedido) throws Exception {
        Pedido p = dao.traer(idPedido);
        if (p == null) {
            throw new Exception("ERROR: El pedido no existe");
        }
        dao.eliminar(p);
    }

    public List<Pedido> traer(UnidadVenta u) {
        return dao.traer(u);
    }
}