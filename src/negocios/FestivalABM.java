package negocios;

import java.time.LocalDate;
import java.util.List;

import dao.FestivalDao;
import datos.Festival;

public class FestivalABM {
	private static FestivalABM instancia = null;
	
	protected FestivalABM() {}
	
	public static FestivalABM getInstance() {
		if(instancia==null)
		   instancia=new FestivalABM();
		return instancia;
	}
	
    public int agregar(String nombre, String temporada, LocalDate inicio, LocalDate fin) throws Exception {
        if (traer(nombre) != null) {
            throw new Exception("ERROR: Ya existe un festival con el nombre " + nombre);
        }
        Festival f = new Festival(nombre, temporada, inicio, fin);
        return FestivalDao.getInstance().agregar(f);
    }

    public void modificar(Festival f) throws Exception {
        if (traer(f.getNombre()) == null) {
            throw new Exception("ERROR: No existe un festival con el nombre " + f.getNombre());
        }
        FestivalDao.getInstance().actualizar(f);
    }

    public void eliminar(long idFestival) throws Exception {
        Festival f = FestivalDao.getInstance().traer(idFestival);
        
        if (f == null) {
            throw new Exception("ERROR: El festival no existe");
        }
        
        if (f.getUnidadesDeVenta() != null) {
            throw new Exception("ERROR: No se puede eliminar el festival porque posee unidades de venta asociadas");
        }
        
        FestivalDao.getInstance().eliminar(f);
    }
    
    public Festival traer(long idFestival) {
    	return FestivalDao.getInstance().traer(idFestival);
    }
    
    public Festival traer(String nombre) {
        return FestivalDao.getInstance().traer(nombre);
    }

    public List<Festival> traer() {
        return FestivalDao.getInstance().traer();
    }
    
    public Festival traerFestivalYUnidadesDeVenta(long idFestival) {
    	return FestivalDao.getInstance().traerFestivalYUnidadesVenta(idFestival);
    }
}