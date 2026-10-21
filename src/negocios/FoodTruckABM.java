package negocios;

import java.util.List;

import dao.FoodTruckDao;
import datos.FoodTruck;

public class FoodTruckABM {
	private static FoodTruckABM instancia = null;
	
	protected FoodTruckABM() {}
	
	public static FoodTruckABM getInstance() {
		if(instancia==null)
		   instancia=new FoodTruckABM();
		return instancia;
	}
	
    public FoodTruck traer(String codigo) {
        return FoodTruckDao.getInstance().traer(codigo);
    }

    public void modificar(FoodTruck f) throws Exception {
        if (traer(f.getCodigo()) == null) {
            throw new Exception("ERROR: No existe un FoodTruck con el codigo " + f.getCodigo());
        }
        FoodTruckDao.getInstance().actualizar(f);
    }

    public void eliminar(String codigo) throws Exception {
        FoodTruck f = FoodTruckDao.getInstance().traer(codigo);
        if (f == null) {
            throw new Exception("ERROR: El FoodTruck no existe");
        }
        FoodTruckDao.getInstance().eliminar(f);
    }

    public List<FoodTruck> traer() {
        return FoodTruckDao.getInstance().traer();
    }
}