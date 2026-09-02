package negocios;

import java.util.List;

import org.hibernate.HibernateException;

import dao.FestivalDao;
import dao.PlatoDao;
import datos.Plato;
import datos.UnidadVenta;

public class PlatoABM {
	private static PlatoABM instancia = null;
	
	protected PlatoABM() {}
	
	public static PlatoABM getInstance() {
		if(instancia==null)
		   instancia=new PlatoABM();
		return instancia;
	}
	
    public int agregar(Plato p) throws Exception {    
        return PlatoDao.getInstance().agregar(p);
    }

    public void modificar(Plato p) throws Exception {
    	PlatoDao.getInstance().actualizar(p);
    }

    public void eliminar(long idPlato) throws Exception {
        Plato p = PlatoDao.getInstance().traer(idPlato);
        if (p == null) {
            throw new Exception("ERROR: El plato no existe");
        }
        PlatoDao.getInstance().eliminar(p);
    }
    
    public Plato traer(long idPlato) {
    	return PlatoDao.getInstance().traer(idPlato);
    }
    
    public Plato traer(String nombre) {
        return PlatoDao.getInstance().traer(nombre);
    }

    public List<Plato> traer() throws HibernateException {
    	return PlatoDao.getInstance().traer();
    }
    
    public List<Plato> traer(UnidadVenta u) {
        return PlatoDao.getInstance().traer(u);
    }
    
    public List<Plato> traerByFestival(long idFestival) throws Exception {
    	if(FestivalDao.getInstance().traer(idFestival) == null) {
    		throw new Exception("ERROR: No existe un festival con id=" + idFestival);
    	}
    	return PlatoDao.getInstance().traerByIdFestiva(idFestival);
    }
}