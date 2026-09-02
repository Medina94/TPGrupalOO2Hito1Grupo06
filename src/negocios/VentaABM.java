package negocios;

import java.util.List;
import dao.VentaDao;
import datos.Venta;
import datos.Pedido;

public class VentaABM {
	private static VentaABM instancia = null;
	
	protected VentaABM() {}
	
	public static VentaABM getInstance() {
		if(instancia==null)
		   instancia=new VentaABM();
		return instancia;
	}
	    
    public int agregar(Venta v) throws Exception {
        return VentaDao.getInstance().agregar(v);
    }

    public void modificar(Venta v) throws Exception {
        if (traer(v.getIdVenta()) == null) {
            throw new Exception("ERROR: No existe venta con ID " + v.getIdVenta());
        }
        VentaDao.getInstance().actualizar(v);
    }

    public void eliminar(long id) throws Exception {
        Venta v = VentaDao.getInstance().traer(id);
        if (v == null) {
            throw new Exception("ERROR: La venta no existe");
        }
        VentaDao.getInstance().eliminar(v);
    }
    
    public Venta traer(long id) {
        return VentaDao.getInstance().traer(id);
    }

    public List<Venta> traer(Pedido p) {
        return VentaDao.getInstance().traer(p);
    }
}