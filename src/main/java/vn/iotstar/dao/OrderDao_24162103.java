package vn.iotstar.dao;

import java.util.List;
import jakarta.persistence.EntityManager;
import vn.iotstar.configs.JPAConfig_24162103;
import vn.iotstar.entity.Order_24162103;
import vn.iotstar.entity.OrderDetail_24162103;

public class OrderDao_24162103 {

    public void insertOrder(Order_24162103 order, List<OrderDetail_24162103> details) {
        EntityManager em = JPAConfig_24162103.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(order);
            for (OrderDetail_24162103 d : details) {
                d.setOrder(order);
                em.persist(d);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public List<Order_24162103> findByUsername(String username) {
        EntityManager em = JPAConfig_24162103.getEntityManager();
        try {
            return em.createQuery(
                "SELECT o FROM Order_24162103 o WHERE o.username = :u ORDER BY o.orderId DESC",
                Order_24162103.class)
                .setParameter("u", username)
                .getResultList();
        } finally {
            em.close();
        }
    }
}