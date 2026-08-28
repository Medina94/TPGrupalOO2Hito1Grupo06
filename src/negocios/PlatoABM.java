package negocios;

import java.util.List;
import dao.PlatoDao;
import datos.Plato;
import datos.UnidadVenta;

public class PlatoABM {
    private PlatoDao dao = new PlatoDao();

    public Plato traer(String nombre) {
        return dao.traer(nombre);
    }
    
    public Plato traer(long idPlato) {
    	return dao.traer(idPlato);
    }

    public int agregar(Plato p) throws Exception {
    
        
        return dao.agregar(p);
    }

    public void modificar(Plato p) throws Exception {
       
        dao.actualizar(p);
    }

    public void eliminar(String nombre) throws Exception {
        Plato p = dao.traer(nombre);
        if (p == null) {
            throw new Exception("ERROR: El plato no existe");
        }
        dao.eliminar(p);
    }

    public List<Plato> traer(UnidadVenta u) {
        return dao.traer(u);
    }
}