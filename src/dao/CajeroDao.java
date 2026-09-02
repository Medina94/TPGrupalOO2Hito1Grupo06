package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import datos.Cajero;

public class CajeroDao {
	
	
	private static Session session;
	private Transaction tx;
	
	private void iniciaOperacion() throws HibernateException {
		session = HibernateUtil.getSessionFactory().openSession();
		tx = session.beginTransaction();
	}

	private void manejaExcepcion(HibernateException he) throws HibernateException {
		tx.rollback();
		throw new HibernateException("ERROR en la capa de acceso a datos", he);
	}


	public void actualizar(Cajero objeto) {
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

	public void eliminar(Cajero objeto) {
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

	

	public Cajero traer(int dni) {
		Cajero cajero = null;
		try {
			iniciaOperacion();
			cajero = (Cajero) session.createQuery("from Cajero c where c.dni = :dni")
					.setParameter("dni", dni)
					.uniqueResult();
		} finally {
			session.close();
		}
		return cajero;
	}

	public List<Cajero> traer() {
		List<Cajero> lista = new ArrayList<Cajero>();
		try {
			iniciaOperacion();
			Query<Cajero> query = session.createQuery("from Cajero c order by c.idEmpleado asc",Cajero.class);
			lista = query.getResultList();
		} finally {
			session.close();
		}
		return lista;
	}
}