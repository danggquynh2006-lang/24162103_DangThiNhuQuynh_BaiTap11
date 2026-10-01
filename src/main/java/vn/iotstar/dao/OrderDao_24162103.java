package vn.iotstar.dao;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
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

    // Loc lich su don hang theo trang thai. status = null hoac rong = lay tat ca
    public List<Order_24162103> findByUsernameAndStatus(String username, String status) {
        EntityManager em = JPAConfig_24162103.getEntityManager();
        try {
            String jpql = "SELECT o FROM Order_24162103 o WHERE o.username = :u";
            if (status != null && !status.trim().isEmpty()) {
                jpql += " AND o.status = :s";
            }
            jpql += " ORDER BY o.orderId DESC";

            TypedQuery<Order_24162103> query = em.createQuery(jpql, Order_24162103.class);
            query.setParameter("u", username);
            if (status != null && !status.trim().isEmpty()) {
                query.setParameter("s", status);
            }
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // Lay chi tiet 1 don hang (JOIN FETCH de tranh LazyInitializationException)
    public Order_24162103 findByIdWithDetails(Integer orderId, String username) {
        EntityManager em = JPAConfig_24162103.getEntityManager();
        try {
            List<Order_24162103> list = em.createQuery(
                "SELECT DISTINCT o FROM Order_24162103 o JOIN FETCH o.details d JOIN FETCH d.video WHERE o.orderId = :id AND o.username = :u",
                Order_24162103.class)
                .setParameter("id", orderId)
                .setParameter("u", username)
                .getResultList();
            return list.isEmpty() ? null : list.get(0);
        } finally {
            em.close();
        }
    }
}