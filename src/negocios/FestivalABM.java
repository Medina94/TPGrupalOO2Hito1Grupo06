package negocios;

import java.time.LocalDate;
import java.util.List;
import dao.FestivalDao;
import datos.Festival;

public class FestivalABM {
    private FestivalDao dao = new FestivalDao();

<<<<<<< HEAD
=======
    public Festival traer(String nombre) {
        return dao.traer(nombre);
    }

>>>>>>> c8e297fe96c8bc76cef998c427cea5863f7bc759
    public int agregar(String nombre, String temporada, LocalDate inicio, LocalDate fin) throws Exception {
        if (traer(nombre) != null) {
            throw new Exception("ERROR: Ya existe un festival con el nombre " + nombre);
        }
        Festival f = new Festival(nombre, temporada, inicio, fin);
        return dao.agregar(f);
    }

    public void modificar(Festival f) throws Exception {
        if (traer(f.getNombre()) == null) {
            throw new Exception("ERROR: No existe un festival con el nombre " + f.getNombre());
        }
        dao.actualizar(f);
    }

<<<<<<< HEAD
    public void eliminar(long idFestival) throws Exception {
        Festival f = dao.traer(idFestival);
        
        if (f == null) {
            throw new Exception("ERROR: El festival no existe");
        }
        
        if (f.getUnidadesDeVenta() != null) {
            throw new Exception("ERROR: No se puede eliminar el festival porque posee unidades de venta asociadas");
        }
        
        dao.eliminar(f);
    }
    
    public Festival traer(long idFestival) {
    	return dao.traer(idFestival);
    }
    
    public Festival traer(String nombre) {
        return dao.traer(nombre);
    }
=======
    public void eliminar(String nombre) throws Exception {
        Festival f = dao.traer(nombre);
        if (f == null) {
            throw new Exception("ERROR: El festival no existe");
        }
        dao.eliminar(f);
    }
>>>>>>> c8e297fe96c8bc76cef998c427cea5863f7bc759

    public List<Festival> traer() {
        return dao.traer();
    }
<<<<<<< HEAD
    
    public Festival traerFestivalYUnidadesDeVenta(long idFestival) {
    	return dao.traerFestivalYUnidadesVenta(idFestival);
    }
=======
>>>>>>> c8e297fe96c8bc76cef998c427cea5863f7bc759
}