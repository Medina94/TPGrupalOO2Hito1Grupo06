package dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import datos.Venta;
import datos.Pedido;
import datos.UnidadVenta;

public class PedidoDao {
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

    public int agregar(Pedido objeto) {
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

    public void actualizar(Pedido objeto) {
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

    public void eliminar(Pedido objeto) {
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

    public Pedido traer(long idPedido) {
        Pedido objeto = null;
        try {
            iniciaOperacion();
            objeto = (Pedido)session.get(Pedido.class, idPedido);
        } finally {
            session.close();
        }
        return objeto;
    }

    public List<Pedido> traer() throws HibernateException{
        List<Pedido> lista = null;
        try {
            iniciaOperacion();     
            lista = session.createQuery("from Pedido p order by p.idPedido asc", Pedido.class).getResultList();
        } finally {
            session.close();
        }
        return lista;
    }
    
    public List<Pedido> traer(UnidadVenta u) {
    	List<Pedido> lista = null;
    	try {
    		iniciaOperacion();
    		String hQL = "from Pedido p inner join fetch p.unidadVenta u where u.idUnidadVenta=:idUnidadVenta";
    		lista = session.createQuery(hQL,Pedido.class).setParameter("idUnidadVenta",u.getIdUnidadVenta()).getResultList();
    	} finally {
    		session.close();
    	}
    	return lista;
    }

    public Pedido traerPedidoYVentas(long idPedido) throws HibernateException {
        Pedido objeto = null;
        try {
            iniciaOperacion();
            String hql = "from Pedido p left join fetch p.festival left join fetch p.unidadVenta where p.idPedido = :idPedido";
            objeto = (Pedido)session.createQuery(hql).setParameter("idPedido", idPedido).uniqueResult();
            Hibernate.initialize(objeto.getVentas());  
            for (Venta v : objeto.getVentas()) {
                Hibernate.initialize(v.getPlato());
            }
        } finally {
            session.close();
        }
        return objeto;
    }
}