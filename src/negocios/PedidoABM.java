package negocios;

<<<<<<< HEAD
import java.time.LocalDate;
import java.util.List;
import dao.PedidoDao;

=======
import java.util.List;
import dao.PedidoDao;
>>>>>>> c8e297fe96c8bc76cef998c427cea5863f7bc759
import datos.Pedido;
import datos.UnidadVenta;

public class PedidoABM {
    private PedidoDao dao = new PedidoDao();

<<<<<<< HEAD
    public int agregar(Pedido p) throws Exception {
    	p.setFecha(LocalDate.now());
=======
    public Pedido traer(long idPedido) {
        return dao.traer(idPedido);
    }

    public int agregar(Pedido p) throws Exception {
  
>>>>>>> c8e297fe96c8bc76cef998c427cea5863f7bc759
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

<<<<<<< HEAD
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
=======
    public List<Pedido> traer(UnidadVenta u) {
        return dao.traer(u);
>>>>>>> c8e297fe96c8bc76cef998c427cea5863f7bc759
    }
}