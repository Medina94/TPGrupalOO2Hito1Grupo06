package negocios;

import java.util.List;

import org.hibernate.HibernateException;

import dao.PlatoDao;
import datos.Plato;
import datos.UnidadVenta;

public class PlatoABM {
    private PlatoDao dao = new PlatoDao();

    public int agregar(Plato p) throws Exception {    
        return dao.agregar(p);
    }

    public void modificar(Plato p) throws Exception {
        dao.actualizar(p);
    }

    public void eliminar(long idPlato) throws Exception {
        Plato p = dao.traer(idPlato);
        if (p == null) {
            throw new Exception("ERROR: El plato no existe");
        }
        dao.eliminar(p);
    }
    
    public Plato traer(long idPlato) {
    	return dao.traer(idPlato);
    }
    
    public Plato traer(String nombre) {
        return dao.traer(nombre);
    }

    public List<Plato> traer() throws HibernateException {
    	return dao.traer();
    }
    
    public List<Plato> traer(UnidadVenta u) {
        return dao.traer(u);
    }
}