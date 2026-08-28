package dao;

import datos.Empleado;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EmpleadoDao {
    private Session session;
    private Transaction tx;

    private void iniciaOperacion() throws HibernateException {
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
    }

    private void manejaExcepcion(HibernateException he) throws HibernateException {
        tx.rollback();
        throw new HibernateException("ERROR en la capa de acceso a datos de Empleado", he);
    }

    public long agregar(Empleado objeto) {
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

    public Empleado traer(long idEmpleado) {
        Empleado objeto = null;
        try {
            iniciaOperacion();

            objeto = session.get(Empleado.class, idEmpleado);
        } finally {
            session.close();
        }
        return objeto;
    }

    public Empleado traer(int dni) {
        Empleado objeto = null;
        try {
            iniciaOperacion();
    
            objeto = session.createQuery("from Empleado e where e.dni = :dni", Empleado.class)
                    .setParameter("dni", dni)
                    .uniqueResult();
        } finally {
            session.close();
        }
        return objeto;
    }
    
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
}