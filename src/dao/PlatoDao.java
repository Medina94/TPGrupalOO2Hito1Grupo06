package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import datos.Plato;
import datos.UnidadVenta;

public class PlatoDao {
    private Session session;
    private Transaction tx;
    private static PlatoDao instancia = null;
    
    protected PlatoDao() {}
    
    public static PlatoDao getInstance() {
    	if(instancia==null)
    	   instancia=new PlatoDao();
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

    public int agregar(Plato objeto) {
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

    public void actualizar(Plato objeto) {
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

    public void eliminar(Plato objeto) {
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

    public Plato traer(long idPlato) {
        Plato plato = null;
        try {
            iniciaOperacion();
            plato = session.get(Plato.class, idPlato);
        } finally {
            session.close();
        }
        return plato;
    }
    
    public Plato traer(String nombre) {
        Plato objeto = null;
        try {
            iniciaOperacion();
            
            objeto = (Plato) session.createQuery("from Plato p where p.nombre=:nombre")
                    .setParameter("nombre", nombre).uniqueResult();
        } finally {
            session.close();
        }
        return objeto;
    }

    public List<Plato> traer() throws HibernateException{
        List<Plato> lista = null;
        try {
            iniciaOperacion();
            lista = session.createQuery("from Plato p order by p.nombre asc", Plato.class).getResultList();
        } finally {
            session.close();
        }
        return lista;
    }
    
    public List<Plato> traer(UnidadVenta u){
    	List<Plato>  lista=null;
    	try {
    		iniciaOperacion();
    		String hQL= "from Plato p inner join fetch p.unidadVenta u where u.idUnidadVenta=:idUnidadVenta";
    		lista = session.createQuery(hQL, Plato.class).setParameter("idUnidadVenta",u.getIdUnidadVenta()).getResultList();
    	}finally {
    		session.close();
    	}
    	return lista;
    }
}