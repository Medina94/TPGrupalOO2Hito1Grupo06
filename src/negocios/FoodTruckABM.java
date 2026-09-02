package negocios;

import java.util.List;
import dao.FoodTruckDao;
import datos.FoodTruck;

public class FoodTruckABM {
    private FoodTruckDao dao = new FoodTruckDao();

    public FoodTruck traer(String codigo) {
        return dao.traer(codigo);
    }

    public void modificar(FoodTruck f) throws Exception {
        if (traer(f.getCodigo()) == null) {
            throw new Exception("ERROR: No existe un FoodTruck con el codigo " + f.getCodigo());
        }
        dao.actualizar(f);
    }

    public void eliminar(String codigo) throws Exception {
        FoodTruck f = dao.traer(codigo);
        if (f == null) {
            throw new Exception("ERROR: El FoodTruck no existe");
        }
        dao.eliminar(f);
    }

    public List<FoodTruck> traer() {
        return dao.traer();
    }
}