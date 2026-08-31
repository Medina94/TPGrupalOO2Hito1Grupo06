package dao;

<<<<<<< HEAD
import java.util.List;

=======
import datos.Empleado;
>>>>>>> c8e297fe96c8bc76cef998c427cea5863f7bc759
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

<<<<<<< HEAD
import datos.Empleado;
import datos.UnidadVenta;

public class EmpleadoDao {
    private static Session session;
    private Transaction tx;

    private static EmpleadoDao instancia = null;
    
    protected EmpleadoDao() {}
    
    public static EmpleadoDao getInstance() {
    	if(instancia==null)
    	   instancia=new EmpleadoDao();
    	return instancia;
    }
    
=======
public class EmpleadoDao {
    private Session session;
    private Transaction tx;

>>>>>>> c8e297fe96c8bc76cef998c427cea5863f7bc759
    private void iniciaOperacion() throws HibernateException {
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
    }

    private void manejaExcepcion(HibernateException he) throws HibernateException {
        tx.rollback();
        throw new HibernateException("ERROR en la capa de acceso a datos de Empleado", he);
    }

<<<<<<< HEAD
    public int agregar(Empleado objeto) {
        int id = 0;
        try {
            iniciaOperacion();
            id = Integer.parseInt(session.save(objeto).toString());
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
=======
    public long agregar(Empleado objeto) {
        long id = 0;
        try {
            iniciaOperacion();
            id = (long) session.save(objeto);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
>>>>>>> c8e297fe96c8bc76cef998c427cea5863f7bc759
        } finally {
            session.close();
        }
        return id;
    }
<<<<<<< HEAD
    
    public void actualizar(Empleado objeto) {
    	try {
    		iniciaOperacion();
    		session.update(objeto);
    		tx.commit();
    	}catch(HibernateException he) {
    		manejaExcepcion(he);
    		throw he;
    	}finally {
    		session.close();
    	}	
    }
    
    public void eliminar(Empleado objeto) {
    	try {
    		iniciaOperacion();
    		session.delete(objeto);
    		tx.commit();
    	}catch(HibernateException he) {
    		manejaExcepcion(he);
    		throw he;
    	}finally {
    		session.close();
    	}
    }
=======
>>>>>>> c8e297fe96c8bc76cef998c427cea5863f7bc759

    public Empleado traer(long idEmpleado) {
        Empleado objeto = null;
        try {
            iniciaOperacion();
<<<<<<< HEAD
            objeto =(Empleado) session.get(Empleado.class, idEmpleado);
=======

            objeto = session.get(Empleado.class, idEmpleado);
>>>>>>> c8e297fe96c8bc76cef998c427cea5863f7bc759
        } finally {
            session.close();
        }
        return objeto;
<<<<<<< HEAD
    } 
=======
    }
>>>>>>> c8e297fe96c8bc76cef998c427cea5863f7bc759

    public Empleado traer(int dni) {
        Empleado objeto = null;
        try {
            iniciaOperacion();
<<<<<<< HEAD
            objeto = (Empleado)session.createQuery("from Empleado e where e.dni = :dni")
                    .setParameter("dni", dni).uniqueResult();
=======
    
            objeto = session.createQuery("from Empleado e where e.dni = :dni", Empleado.class)
                    .setParameter("dni", dni)
                    .uniqueResult();
>>>>>>> c8e297fe96c8bc76cef998c427cea5863f7bc759
        } finally {
            session.close();
        }
        return objeto;
    }
    
<<<<<<< HEAD
    public List<Empleado> traer() throws HibernateException{
    	List<Empleado> lista = null;
    	try {
    		iniciaOperacion();
    		lista= session.createQuery("from Empleado", Empleado.class).list();
    	} finally {
    		session.close();
    	}
    	return lista;
    }
    
    public List<Empleado> traer(UnidadVenta u) {
    	List<Empleado> lista = null;
    	try {
    		iniciaOperacion();
    		String hQL = "from Empleado e inner join fetch e.unidadVenta u where u.idUnidadVenta=:idUnidadVenta";
    		lista= session.createQuery(hQL,Empleado.class).setParameter("idUnidadVenta",u.getIdUnidadVenta()).getResultList();
    	}finally {
    		session.close();
    	}
    	return lista;
    }
 
=======
    public void eliminar(Empleado empleado) {
    	try {
    		iniciaOperacion();
    		session.delete(empleado);
    		tx.commit();
    	}catch(HibernateException he) {
    		manejaExcepcion(he);
    		throw he;
    	}finally {
    		session.close();
    	}
    }
    public void actualizar(Empleado empleado) {
    	try {
    		iniciaOperacion();
    		session.update(empleado);
    		tx.commit();
    	}catch(HibernateException he) {
    		manejaExcepcion(he);
    		throw he;
    	}finally {
    		session.close();
    	}
    	
    }
>>>>>>> c8e297fe96c8bc76cef998c427cea5863f7bc759
}