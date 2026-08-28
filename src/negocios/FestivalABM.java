package negocios;

import java.time.LocalDate;
import java.util.List;
import dao.FestivalDao;
import datos.Festival;

public class FestivalABM {
    private FestivalDao dao = new FestivalDao();

    public Festival traer(String nombre) {
        return dao.traer(nombre);
    }

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

    public void eliminar(String nombre) throws Exception {
        Festival f = dao.traer(nombre);
        if (f == null) {
            throw new Exception("ERROR: El festival no existe");
        }
        dao.eliminar(f);
    }

    public List<Festival> traer() {
        return dao.traer();
    }
}