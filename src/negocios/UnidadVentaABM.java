package negocios;

import java.util.List;
import dao.UnidadVentaDao;

import datos.UnidadVenta;
import datos.PuestoDesarmable;
import datos.Empleado;
import datos.Festival;
import datos.FoodTruck;
import datos.Plato;

public class UnidadVentaABM {
	
	private static UnidadVentaABM instancia = null;
	
	public static UnidadVentaABM getInstance() {
		if(instancia==null)
		   instancia=new UnidadVentaABM();
		return instancia;
	}
	
	public int agregarFoodTruck(String nombreComercial, Empleado responsable, double superficie, String codigo,String patente,boolean conexionElectrica,Festival festival)throws Exception{
		if(codigo.length()!=10) {
        	throw new Exception("ERROR: El codigo debe contener 10 caracteres");
        }
		if(UnidadVentaDao.getInstance().traer(codigo)!=null) {
        	throw new Exception("ERROR: Ya existe una unidad de venta con el codigo:" + codigo);
        }
		FoodTruck foodTruck = new FoodTruck(nombreComercial,responsable,superficie,codigo,patente,conexionElectrica,festival);
		if(conexionElectrica == true) {
			foodTruck.setPlusPorElectricidad(calcularPlusElectricidad());
		}
		foodTruck.setCostosSuperficie(calcularCostosSuperficie(superficie));
		return UnidadVentaDao.getInstance().agregar(foodTruck);
	}
	
	public int agregarPuestoDesarmable(String nombreComercial, Empleado responsable, double superficie, String codigo,int cantidadCarpas, int minutos, Festival festival)throws Exception{
		if(codigo.length()!=10) {
        	throw new Exception("ERROR: El codigo debe contener 10 caracteres");
        }
		if(UnidadVentaDao.getInstance().traer(codigo)!=null) {
        	throw new Exception("ERROR: Ya existe una unidad de venta con el codigo:" + codigo);
        }
		PuestoDesarmable puestoDesarmable = new PuestoDesarmable(nombreComercial,responsable,superficie,codigo,cantidadCarpas,minutos,festival);
		puestoDesarmable.setCostosPorMontaje(calcularCostosMontaje(minutos,cantidadCarpas));
		puestoDesarmable.setCostosSuperficie(calcularCostosSuperficie(superficie));
		return UnidadVentaDao.getInstance().agregar(puestoDesarmable);
	}
	
    
    public void modificar(UnidadVenta u) throws Exception {
        if (traer(u.getCodigo()) == null) {
            throw new Exception("ERROR: No existe una unidad de venta con el codigo" + u.getCodigo());
        }
        UnidadVentaDao.getInstance().actualizar(u);
    }

    public void eliminar(long idUnidadVenta) throws Exception {
        UnidadVenta u =  UnidadVentaDao.getInstance().traerUnidadVentaYEmpleados(idUnidadVenta);
        if (u == null) {
            throw new Exception("ERROR: La unidad de venta no existe");
        }

        // 1. Desvincular el responsable
        u.setResponsable(null);

        // 2. Liberar los empleados asociados (limpiar la FK en la entidad Empleado)
        if (u.getEmpleados() != null ) {
            for (Empleado emp : u.getEmpleados()) {
                emp.setUnidadVenta(null);
            }
            u.getEmpleados().clear();
        }

        if (u.getPlatos() != null) {
            for (Plato p : u.getPlatos()) {
                p.setUnidadVenta(null);
            }
            u.getPlatos().clear();
        }
        // 3. Validar otras dependencias que impidan borrar (platos, pedidos, etc.)
        if (u.getPlatos() != null ) {
            throw new Exception("ERROR: No se puede eliminar la unidad de venta porque posee platos asociados");
        }

        // 4. Actualizar estado y eliminar
        UnidadVentaDao.getInstance().actualizar(u);
        UnidadVentaDao.getInstance().eliminar(u);
    }
    
    public UnidadVenta traer(long idUnidadVenta) {
    	return UnidadVentaDao.getInstance().traer(idUnidadVenta);
    }
    
    public UnidadVenta traer(String codigo) {
        return UnidadVentaDao.getInstance().traer(codigo);
    }

    public List<UnidadVenta> traer() {
        return UnidadVentaDao.getInstance().traer();
    }
    
    public UnidadVenta traerUnidadVentaYEmpleados(long idUnidadVenta) {
    	return UnidadVentaDao.getInstance().traerUnidadVentaYEmpleados(idUnidadVenta);
    }
    
    public UnidadVenta traerUnidadVentaYPlatos(long idUnidadVenta) {
    	return UnidadVentaDao.getInstance().traerUnidadVentaYPlatos(idUnidadVenta);
    }
    public UnidadVenta traerUnidadVentaYPedidos(long idUnidadVenta) {
    	return UnidadVentaDao.getInstance().traerUnidadVentaYPedidos(idUnidadVenta);
    }
    
    public double calcularCostosSuperficie(double superficie) {
    	double costo;
    	costo = superficie*2000.0;
    	return costo;
    }
    
    public double calcularCostosMontaje(int minutos,int cantidadCarpas) {
    	double costo;
    	costo= minutos * cantidadCarpas * 2000;
    	return costo;
    }
    
    public double calcularPlusElectricidad() {
    	double costo=20000;
    	
    	return costo;
    }

}