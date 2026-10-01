package vn.iotstar.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import vn.iotstar.configs.JPAConfig_24162103;
import vn.iotstar.entity.User_24162103;

public class UserDao_24162103 {
    public User_24162103 findById(String username) {
        EntityManager enma = JPAConfig_24162103.getEntityManager();
        try {
            return enma.find(User_24162103.class, username);
        } finally {
            enma.close();
        }
    }

    public void insert(User_24162103 user) {
        EntityManager enma = JPAConfig_24162103.getEntityManager();
        try {
            enma.getTransaction().begin();
            enma.persist(user);
            enma.getTransaction().commit();
        } catch (Exception e) {
            enma.getTransaction().rollback();
            throw e;
        } finally {
            enma.close();
        }
    }
}