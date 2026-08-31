package dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import datos.FoodTruck;

public class FoodTruckDao {
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

    public void actualizar(FoodTruck objeto) {
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

    public void eliminar(FoodTruck objeto) {
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

    public FoodTruck traer(String codigo) {
        FoodTruck foodTruck = null;
        try {
            iniciaOperacion();
            foodTruck = (FoodTruck) session.createQuery("from FoodTruck f where f.codigo = :codigo")
                    .setParameter("codigo", codigo)
                    .uniqueResult();
        } finally {
            session.close();
        }
        return foodTruck;
    }

    public List<FoodTruck> traer() {
        List<FoodTruck> lista = new ArrayList<>();
        try {
            iniciaOperacion();
            Query<FoodTruck> query = session.createQuery("from FoodTruck f order by f.codigo asc", FoodTruck.class);
            lista = query.getResultList();
        } finally {
            session.close();
        }
        return lista;
    }
}