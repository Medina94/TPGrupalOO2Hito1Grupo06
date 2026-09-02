package negocios;

import java.time.LocalDate;
import java.util.List;
import dao.PedidoDao;

import datos.Pedido;
import datos.UnidadVenta;

public class PedidoABM {
	private static PedidoABM instancia = null;
	
	protected PedidoABM() {}
	
	public static PedidoABM getInstance() {
		if(instancia==null)
		   instancia=new PedidoABM();
		return instancia;
	}
	
    public int agregar(Pedido p) throws Exception {
    	p.setFecha(LocalDate.now());
        return PedidoDao.getInstance().agregar(p);
    }

    public void modificar(Pedido p) throws Exception {
        if (traer(p.getIdPedido()) == null) {
            throw new Exception("ERROR: No existe el pedido con ID " + p.getIdPedido());
        }
        PedidoDao.getInstance().actualizar(p);
    }

    public void eliminar(long idPedido) throws Exception {
        Pedido p = PedidoDao.getInstance().traer(idPedido);
        if (p == null) {
            throw new Exception("ERROR: El pedido no existe");
        }
        PedidoDao.getInstance().eliminar(p);
    }

    public Pedido traer(long idPedido) {
        return PedidoDao.getInstance().traer(idPedido);
    }
    
    public List<Pedido> traer() {
        return PedidoDao.getInstance().traer();
    }
    
    public List<Pedido> traer(UnidadVenta u){
    	return PedidoDao.getInstance().traer(u);
    }
    
    public Pedido traerPedidoYVentas(long idPedido) {
    	return PedidoDao.getInstance().traerPedidoYVentas(idPedido);
    }
}