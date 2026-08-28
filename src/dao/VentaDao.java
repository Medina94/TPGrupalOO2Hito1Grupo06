package dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import datos.Venta;
import datos.Pedido;


public class VentaDao {
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

    public int agregar(Venta objeto) {
        int id = 0;
        try {
            iniciaOperacion();
            id = Integer.parseInt(session.save(objeto).toString());
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
        } finally {
            session.close();
        }
        return id;
    }

    public void actualizar(Venta objeto) {
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

    public void eliminar(Venta objeto) {
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

    public Venta traer(long idVenta) {
        Venta venta = null;
        try {
            iniciaOperacion();
			String hQL = "from Venta v inner join fetch v.pedido p where v.idVenta=:idVenta";
			venta = (Venta) session.createQuery(hQL).setParameter("idVenta", idVenta).uniqueResult();
        } finally {
            session.close();
        }
        return venta;
    }

    public List<Venta> traer(Pedido p) {
        List<Venta> lista = null;
        try {
            iniciaOperacion();
            String hQL = "from Venta v inner join fetch v.pedido p where p.idPedido=:idPedido";
            lista = session.createQuery(hQL,Venta.class).setParameter("idPedido",p.getIdPedido()).getResultList();
        } finally {
            session.close();
        }
        return lista;
    }
}