package negocios;

import java.time.LocalDate;
import java.util.List;
import dao.PedidoDao;

import datos.Pedido;
import datos.UnidadVenta;

public class PedidoABM {
    private PedidoDao dao = new PedidoDao();

    public int agregar(Pedido p) throws Exception {
    	p.setFecha(LocalDate.now());
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

    public Pedido traer(long idPedido) {
        return dao.traer(idPedido);
    }
    
    public List<Pedido> traer() {
        return dao.traer();
    }
    
    public List<Pedido> traer(UnidadVenta u){
    	return dao.traer(u);
    }
    
    public Pedido traerPedidoYVentas(long idPedido) {
    	return dao.traerPedidoYVentas(idPedido);
    }
}