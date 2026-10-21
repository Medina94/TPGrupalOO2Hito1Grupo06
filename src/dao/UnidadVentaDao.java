package dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import datos.Festival;
import datos.Pedido;
import datos.UnidadVenta;
import datos.Venta;

public class UnidadVentaDao {
    private static Session session;
    private Transaction tx;

    private static UnidadVentaDao instancia = null;
    
    protected UnidadVentaDao() {}
    
    public static UnidadVentaDao getInstance() {
    	if(instancia==null)
    	   instancia=new UnidadVentaDao();
    	return instancia;
    }
    
    private void iniciaOperacion() throws HibernateException {
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
    }

    private void manejaExcepcion(HibernateException he) throws HibernateException {
        tx.rollback();
        throw new HibernateException("ERROR en la capa de acceso a datos", he);
    }

    public int agregar(UnidadVenta objeto) {
        int id = 0;
        try {
            iniciaOperacion();
            id = Integer.parseInt(session.save(objeto).toString());
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            session.close();
        }
        return id;
    }

    public void actualizar(UnidadVenta objeto) {
        try {
            iniciaOperacion();
            session.update(objeto);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            session.close();
        }
    }
    
    public void eliminar(UnidadVenta objeto) {
        try {
            iniciaOperacion();
            session.delete(objeto);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            session.close();
        }
    }

    public UnidadVenta traer(long idUnidadVenta) {
        UnidadVenta objeto = null;
        try {
            iniciaOperacion();
            objeto = (UnidadVenta) session.get(UnidadVenta.class, idUnidadVenta);
        } finally {
            session.close();
        }
        return objeto;
    }

    public UnidadVenta traer(String codigo) {
        UnidadVenta objeto = null;
        try {
            iniciaOperacion();
            objeto = (UnidadVenta) session.createQuery("from UnidadVenta u where u.codigo = :codigo")
                    .setParameter("codigo", codigo).uniqueResult();
        } finally {
            session.close();
        }
        return objeto;
    }
    
    public List<UnidadVenta> traer() throws HibernateException {
        List<UnidadVenta> lista = null;
        try {
            iniciaOperacion();
            lista = session.createQuery("from UnidadVenta u order by u.nombreComercial asc", UnidadVenta.class).getResultList();
        } finally {
            session.close();
        }
        return lista;
    }
    
    public List<UnidadVenta> traer(Festival f){
    	List<UnidadVenta> lista=null;
    	try {
    		iniciaOperacion();
    		String hQL = "from UnidadVenta u inner join fetch u.festival f where f.idFestival=:idFestival";
    		lista = session.createQuery(hQL, UnidadVenta.class).setParameter("idFestival",f.getIdFestival()).getResultList();
    	}finally {
    		session.close();
    	}
    	return lista;
    }

    public UnidadVenta traerUnidadVentaYEmpleados(long idUnidadVenta) throws HibernateException {
        UnidadVenta objeto = null;
        try {
            iniciaOperacion();
            String hql = "from UnidadVenta u left join fetch u.empleados where u.idUnidadVenta = :idUnidadVenta";
            objeto = (UnidadVenta)session.createQuery(hql).setParameter("idUnidadVenta", idUnidadVenta).uniqueResult();
           
            Hibernate.initialize(objeto.getEmpleados());
        } finally {
            session.close();
        }
        return objeto;
    }

    public UnidadVenta traerUnidadVentaYPlatos(long idUnidadVenta) throws HibernateException {
        UnidadVenta objeto = null;
        try {
            iniciaOperacion();
            String hql = "from UnidadVenta u where u.idUnidadVenta = :idUnidadVenta";
            objeto = (UnidadVenta)session.createQuery(hql).setParameter("idUnidadVenta", idUnidadVenta).uniqueResult();
            
            Hibernate.initialize(objeto.getPlatos());     
        } finally {
            session.close();
        }
        return objeto;
    }

    public UnidadVenta traerUnidadVentaYPedidos(long idUnidadVenta) throws HibernateException {
        UnidadVenta objeto = null;
        try {
            iniciaOperacion();
            String hql = "from UnidadVenta u where u.idUnidadVenta = :idUnidadVenta";
            objeto = (UnidadVenta)session.createQuery(hql).setParameter("idUnidadVenta", idUnidadVenta).uniqueResult();
            Hibernate.initialize(objeto.getPedidos());
            for(Pedido p:objeto.getPedidos()) {
            	Hibernate.initialize(p.getVentas());
                for (Venta v : p.getVentas()) {
                    Hibernate.initialize(v.getPlato());
                }
            }
        } finally {
            session.close();
        }
        return objeto;
    }
    
    public UnidadVenta traerUnidadVentaYDependencias(long idUnidadVenta) throws HibernateException{
    	UnidadVenta objeto = null;
        try {
            iniciaOperacion();
            String hql = "from UnidadVenta u left join fetch u.empleados where u.idUnidadVenta = :idUnidadVenta";
            objeto = (UnidadVenta)session.createQuery(hql).setParameter("idUnidadVenta", idUnidadVenta).uniqueResult();
           
            Hibernate.initialize(objeto.getEmpleados());
            Hibernate.initialize(objeto.getPlatos());
            Hibernate.initialize(objeto.getPedidos());
        } finally {
            session.close();
        }
        return objeto;
    }
    
    public List<UnidadVenta> traerPorResponsable(long idEmpleado) throws HibernateException {
        List<UnidadVenta> lista = null;
        try {
            iniciaOperacion();
            String hql = "from UnidadVenta u where u.responsable.idEmpleado = :idEmpleado";
            lista = session.createQuery(hql, UnidadVenta.class).setParameter("idEmpleado", idEmpleado).list();
        } catch (HibernateException he) {
            manejaExcepcion(he);
        } finally {
               session.close();           
        }
        return lista;
    }
    
   
}