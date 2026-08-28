package dao;

import java.util.List;

import datos.UnidadVenta;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UnidadVentaDao {
    private Session session;
    private Transaction tx;

    private void iniciaOperacion() throws HibernateException {
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
    }

    private void manejaExcepcion(HibernateException he) throws HibernateException {
        tx.rollback();
        throw new HibernateException("ERROR en la capa de acceso a datos", he);
    }

    public long agregar(UnidadVenta objeto) {
    	long id = 0;
        try {
            iniciaOperacion();
            id = (long) session.save(objeto);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
        } finally {
            session.close();
        }
        return id;
    }
    
    public UnidadVenta traer(long idUnidadVenta) {
    	UnidadVenta unidad=null;
    	try {
    		iniciaOperacion();
    		unidad=(UnidadVenta)session.get(UnidadVenta.class,idUnidadVenta);
    	} finally {
    		session.close();
    	}
    	return unidad;
    }
    
    public UnidadVenta traer(String codigo) {
        UnidadVenta unidad = null;
        try {
            iniciaOperacion();
            unidad = (UnidadVenta) session.createQuery("from UnidadVenta u where u.codigo = :codigo")
                    .setParameter("codigo", codigo)
                    .uniqueResult();
        } finally {
            session.close();
        }
        return unidad;
    }

    public void eliminar(UnidadVenta objeto) {
        try {
            iniciaOperacion();
            session.delete(objeto);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
        } finally {
            session.close();
        }
    }
    
    public void actualizar(UnidadVenta objeto) {
        try {
            iniciaOperacion();
            session.update(objeto);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
        } finally {
            session.close();
        }
    }

    public List<UnidadVenta> traer() throws HibernateException{
        List<UnidadVenta> lista = null;
        try {
            iniciaOperacion();
            lista = session.createQuery("from UnidadVenta u order by u.nombreComercial asc",UnidadVenta.class).getResultList();
        } finally {
            session.close();
        }
        return lista;
    }
    
    public UnidadVenta traerUnidadYPersonal(long idUnidadVenta) throws HibernateException{
    	UnidadVenta objeto =null;
    	try {
    		iniciaOperacion();
    		String hql = "from UnidadVenta u where u.idUnidadVenta= :idUnidadVenta";
    		objeto=(UnidadVenta) session.createQuery(hql).setParameter("idUnidadVenta", idUnidadVenta).uniqueResult();
            Hibernate.initialize(objeto.getPersonal());
    	}
    	finally {
    		session.close();    	
    	}
    	return objeto;
    }
    
    public UnidadVenta traerUnidadYPlatos(long idUnidadVenta) throws HibernateException{
    	UnidadVenta objeto =null;
    	try {
    		iniciaOperacion();
    		String hql = "from UnidadVenta u where u.idUnidadVenta= :idUnidadVenta";
    		objeto=(UnidadVenta) session.createQuery(hql).setParameter("idUnidadVenta", idUnidadVenta).uniqueResult();
            Hibernate.initialize(objeto.getPlatos());
    	}
    	finally {
    		session.close();    	
    	}
    	return objeto;
    }
    public UnidadVenta traerUnidadYPedidos(long idUnidadVenta) throws HibernateException{
    	UnidadVenta objeto =null;
    	try {
    		iniciaOperacion();
    		String hql = "from UnidadVenta u where u.idUnidadVenta= :idUnidadVenta";
    		objeto=(UnidadVenta) session.createQuery(hql).setParameter("idUnidadVenta", idUnidadVenta).uniqueResult();
            Hibernate.initialize(objeto.getPedidos());
    	}
    	finally {
    		session.close();    	
    	}
    	return objeto;
    }
}