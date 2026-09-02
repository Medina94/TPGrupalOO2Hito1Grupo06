package dao;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import datos.Cocinero;

public class CocineroDao {
	private static Session session;
	private Transaction tx;
	private static CocineroDao instancia = null;
    
    protected CocineroDao() {}
    
    public static CocineroDao getInstance() {
    	if(instancia==null)
    	   instancia=new CocineroDao();
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


	public void actualizar(Cocinero objeto) {
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

	public void eliminar(Cocinero objeto) {
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



	public Cocinero traer(int dni) {
		Cocinero cocinero = null;
		try {
			iniciaOperacion();
			cocinero = (Cocinero) session.createQuery("from Cocinero c where c.dni = :dni")
					.setParameter("dni", dni)
					.uniqueResult();
		} finally {
			session.close();
		}
		return cocinero;
	}

	public List<Cocinero> traer() {
		List<Cocinero> lista = new ArrayList<Cocinero>();
		try {
			iniciaOperacion();
			Query<Cocinero> query = session.createQuery("from Cocinero c order by c.idEmpleado asc",Cocinero.class);
			lista = query.getResultList();
		} finally {
			session.close();
		}
		return lista;
	}
}
