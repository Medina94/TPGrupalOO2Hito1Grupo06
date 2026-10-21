package dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import datos.Pedido;
import datos.UnidadVenta;
import datos.Venta;

public class PedidoDao {
    private Session session;
    private Transaction tx;
    private static PedidoDao instancia = null;
    
    protected PedidoDao() {}
    
    public static PedidoDao getInstance() {
    	if(instancia==null)
    	   instancia=new PedidoDao();
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
            objeto = session.get(Pedido.class, idPedido);
            Hibernate.initialize(objeto.getUnidadVenta()); // inicializa el proxy
            Hibernate.initialize(objeto.getUnidadVenta()); // fuerza la subclase real
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
    
    /**
     * Recibe un idFestival y devuelve todos los pedidos que se realizaron
     * @param idFestival
     * @return
     * @throws HibernateException
     */
    public List<Pedido> traerPedidosFestival(long idFestival) throws HibernateException {
    	List<Pedido> pedidos = new ArrayList<Pedido>();
    	try {
    		iniciaOperacion();
    		String hql = "from Pedido p where p.festival.idFestival=:idFestival";
    		pedidos = session.createQuery(hql, Pedido.class).setParameter("idFestival", idFestival).getResultList();
    		for (Pedido p : pedidos) {
    		    Hibernate.initialize(p.getFestival());
    		    Hibernate.initialize(p.getVentas());
    		    Hibernate.initialize(p.getUnidadVenta());
    		}
    	}finally {
            session.close();
		}
    	return pedidos;
    }
    
    /**
     * Recibe un idFestival y devuelve todos los pedidos que se realizaron en {fecha}
     * @param idFestival
     * @return
     * @throws HibernateException
     */
    public List<Pedido> traerPedidosFestival(long idFestival, LocalDate fecha) throws HibernateException {
    	List<Pedido> pedidos = new ArrayList<Pedido>();
    	try {
    		iniciaOperacion();
    		String hql = "from Pedido p where p.festival.idFestival = :idFestival and p.fecha = :fecha";
    		pedidos = session.createQuery(hql, Pedido.class)
    				.setParameter("idFestival", idFestival)
    				.setParameter("fecha", fecha)
    				.getResultList();

    		for (Pedido p : pedidos) {
    		    Hibernate.initialize(p.getFestival());
    		    Hibernate.initialize(p.getVentas());
    		    Hibernate.initialize(p.getUnidadVenta());
    		}
    		
    	}finally {
            session.close();
		}
    	return pedidos;
    }
    
}