package negocios;

import java.util.List;
import dao.UnidadVentaDao;

import datos.UnidadVenta;

public class UnidadVentaABM {
    private UnidadVentaDao dao = new UnidadVentaDao();

    public UnidadVenta traer(long idUnidadVenta) {
    	return dao.traer(idUnidadVenta);
    }

    
    public UnidadVenta traer(String codigo) {
        return dao.traer(codigo);
    }
 
    public void modificar(UnidadVenta u) throws Exception {
        if (traer(u.getCodigo()) == null) {
            throw new Exception("ERROR: No existe una unidad de venta con el codigo" + u.getCodigo());
        }
        dao.actualizar(u);
    }

    public void eliminar(String codigo) throws Exception {
        UnidadVenta u = dao.traer(codigo);
        if (u == null) {
            throw new Exception("ERROR: La unidad de venta no existe");
        }
        dao.eliminar(u);
    }

    public List<UnidadVenta> traer() {
        return dao.traer();
    }
    
    public UnidadVenta traerUnidadVentaYPersonal(long idUnidadVenta) {
    	return dao.traerUnidadYPersonal(idUnidadVenta);
    }
    public UnidadVenta traerUnidadVentaYPlatos(long idUnidadVenta) {
    	return dao.traerUnidadYPlatos(idUnidadVenta);
    }
    public UnidadVenta traerUnidadVentaYPedidos(long idUnidadVenta) {
    	return dao.traerUnidadYPedidos(idUnidadVenta);
    }
    
    public long agregar(UnidadVenta u) throws Exception {
        
        if(u.getCodigo().length()!=10) {
        	throw new Exception("ERROR: El codigo tiene que tener 10 caracteres");
        }
        
        return dao.agregar(u);
    }

}