package vn.iotstar.dao;

import java.util.List;
import jakarta.persistence.EntityManager;
import vn.iotstar.configs.JPAConfig_24162103;
import vn.iotstar.entity.Cart_24162103;

public class CartDao_24162103 {

	public List<Cart_24162103> findByUsername(String username) {
	    EntityManager em = JPAConfig_24162103.getEntityManager();
	    try {
	        return em.createQuery(
	            "SELECT c FROM Cart_24162103 c JOIN FETCH c.video WHERE c.username = :u ORDER BY c.cartId",
	            Cart_24162103.class)
	            .setParameter("u", username)
	            .getResultList();
	    } finally {
	        em.close();
	    }
	}

	public Cart_24162103 findByUsernameAndVideoId(String username, String videoId) {
	    EntityManager em = JPAConfig_24162103.getEntityManager();
	    try {
	        List<Cart_24162103> list = em.createQuery(
	            "SELECT c FROM Cart_24162103 c JOIN FETCH c.video WHERE c.username = :u AND c.videoId = :v",
	            Cart_24162103.class)
	            .setParameter("u", username)
	            .setParameter("v", videoId)
	            .getResultList();
	        return list.isEmpty() ? null : list.get(0);
	    } finally {
	        em.close();
	    }
	}
    public void insert(Cart_24162103 cart) {
        EntityManager em = JPAConfig_24162103.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cart);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void updateQuantity(Integer cartId, int quantity) {
        EntityManager em = JPAConfig_24162103.getEntityManager();
        try {
            em.getTransaction().begin();
            Cart_24162103 cart = em.find(Cart_24162103.class, cartId);
            if (cart != null) {
                cart.setQuantity(quantity);
                em.merge(cart);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void delete(Integer cartId) {
        EntityManager em = JPAConfig_24162103.getEntityManager();
        try {
            em.getTransaction().begin();
            Cart_24162103 cart = em.find(Cart_24162103.class, cartId);
            if (cart != null) em.remove(cart);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void deleteAllByUsername(String username) {
        EntityManager em = JPAConfig_24162103.getEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Cart_24162103 c WHERE c.username = :u")
                .setParameter("u", username)
                .executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}