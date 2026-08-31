package negocios;

import java.util.List;
<<<<<<< HEAD

import org.hibernate.HibernateException;

=======
>>>>>>> c8e297fe96c8bc76cef998c427cea5863f7bc759
import dao.PlatoDao;
import datos.Plato;
import datos.UnidadVenta;

public class PlatoABM {
    private PlatoDao dao = new PlatoDao();

<<<<<<< HEAD
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
=======
    public Plato traer(String nombre) {
        return dao.traer(nombre);
>>>>>>> c8e297fe96c8bc76cef998c427cea5863f7bc759
    }
    
    public Plato traer(long idPlato) {
    	return dao.traer(idPlato);
    }
<<<<<<< HEAD
    
    public Plato traer(String nombre) {
        return dao.traer(nombre);
    }

    public List<Plato> traer() throws HibernateException {
    	return dao.traer();
    }
    
=======

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

>>>>>>> c8e297fe96c8bc76cef998c427cea5863f7bc759
    public List<Plato> traer(UnidadVenta u) {
        return dao.traer(u);
    }
}