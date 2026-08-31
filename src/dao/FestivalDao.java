package dao;

import java.util.ArrayList;
import java.util.List;
<<<<<<< HEAD

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

=======
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
>>>>>>> c8e297fe96c8bc76cef998c427cea5863f7bc759
import datos.Festival;

public class FestivalDao {
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

    public int agregar(Festival objeto) {
        int id = 0;
        try {
            iniciaOperacion();
            id = Integer.parseInt(session.save(objeto).toString());
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
<<<<<<< HEAD
            throw he;
=======
>>>>>>> c8e297fe96c8bc76cef998c427cea5863f7bc759
        } finally {
            session.close();
        }
        return id;
    }

    public void actualizar(Festival objeto) {
        try {
            iniciaOperacion();
            session.update(objeto);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
<<<<<<< HEAD
            throw he;
=======
>>>>>>> c8e297fe96c8bc76cef998c427cea5863f7bc759
        } finally {
            session.close();
        }
    }

    public void eliminar(Festival objeto) {
        try {
            iniciaOperacion();
            session.delete(objeto);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
<<<<<<< HEAD
            throw he;
=======
>>>>>>> c8e297fe96c8bc76cef998c427cea5863f7bc759
        } finally {
            session.close();
        }
    }

<<<<<<< HEAD
    public Festival traer(long idFestival) {
    	Festival objeto = null;
    	try {
    		iniciaOperacion();
    		objeto = (Festival)session.get(Festival.class,idFestival);
    	}finally {
    		session.close();
    	}
    	return objeto;
    }
    
    public Festival traer(String nombre) {
        Festival objeto = null;
        try {
            iniciaOperacion();
            objeto = (Festival) session.createQuery("from Festival f where f.nombre=:nombre")
                    .setParameter("nombre", nombre).uniqueResult();
        } finally {
            session.close();
        }
        return objeto;
    }

    public List<Festival> traer() throws HibernateException{
        List<Festival> lista = new ArrayList<>();
        try {
            iniciaOperacion();
            lista = session.createQuery("from Festival f order by f.nombre asc", Festival.class).getResultList();     
=======
    public Festival traer(String nombre) {
        Festival festival = null;
        try {
            iniciaOperacion();
            festival = (Festival) session.createQuery("from Festival f where f.nombre = :nombre")
                    .setParameter("nombre", nombre)
                    .uniqueResult();
        } finally {
            session.close();
        }
        return festival;
    }

    public List<Festival> traer() {
        List<Festival> lista = new ArrayList<>();
        try {
            iniciaOperacion();
            Query<Festival> query = session.createQuery("from Festival f order by f.nombre asc", Festival.class);
            lista = query.getResultList();
>>>>>>> c8e297fe96c8bc76cef998c427cea5863f7bc759
        } finally {
            session.close();
        }
        return lista;
    }
    
<<<<<<< HEAD
    public Festival traerFestivalYUnidadesVenta(long idFestival)throws HibernateException{
    	Festival objeto=null;
    	try {
    		iniciaOperacion();
    		String hql = "from Festival f where f.idFestival=:idFestival";
    		objeto=(Festival) session.createQuery(hql).setParameter("idFestival", idFestival).uniqueResult();
    		Hibernate.initialize(objeto.getUnidadesDeVenta());
    	}
    	finally {
    		session.close();
    	}
    	return objeto;
    }
    
=======
>>>>>>> c8e297fe96c8bc76cef998c427cea5863f7bc759
}