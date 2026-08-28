package dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import datos.PuestoDesarmable;

public class PuestoDesarmableDao {
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

    public void actualizar(PuestoDesarmable objeto) {
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

    public void eliminar(PuestoDesarmable objeto) {
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

    public PuestoDesarmable traer(String codigo) {
        PuestoDesarmable puesto = null;
        try {
            iniciaOperacion();
            puesto = (PuestoDesarmable) session.createQuery("from PuestoDesarmable p where p.codigo = :codigo")
                    .setParameter("codigo", codigo)
                    .uniqueResult();
        } finally {
            session.close();
        }
        return puesto;
    }

    public List<PuestoDesarmable> traer() {
        List<PuestoDesarmable> lista = new ArrayList<>();
        try {
            iniciaOperacion();
            Query<PuestoDesarmable> query = session.createQuery("from PuestoDesarmable p order by p.codigo asc", PuestoDesarmable.class);
            lista = query.getResultList();
        } finally {
            session.close();
        }
        return lista;
    }
}