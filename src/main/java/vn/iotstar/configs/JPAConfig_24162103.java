package vn.iotstar.configs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceUnit;

public class JPAConfig_24162103 {
    private static EntityManagerFactory emf;

    public static synchronized EntityManager getEntityManager() {
        if (emf == null || !emf.isOpen()) {
            try {
                emf = Persistence.createEntityManagerFactory("course-data");
            } catch (Exception e) {
                e.printStackTrace();
                throw new IllegalStateException("Khong ket noi duoc CSDL - kiem tra persistence.xml", e);
            }
        }
        return emf.createEntityManager();
    }
    public static void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}