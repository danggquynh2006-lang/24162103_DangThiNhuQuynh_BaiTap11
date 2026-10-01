package vn.iotstar.dao;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import vn.iotstar.configs.JPAConfig_24162103;
import vn.iotstar.entity.Category_24162103;

public class CategoryDao_24162103 {

    public List<Category_24162103> findAll() {
        EntityManager enma = JPAConfig_24162103.getEntityManager();
        try {
            TypedQuery<Category_24162103> query = enma.createQuery(
                    "SELECT c FROM Category_24162103 c ORDER BY c.categoryId", Category_24162103.class);
            return query.getResultList();
        } finally {
            enma.close();
        }
    }

    public Category_24162103 findById(int categoryId) {
        EntityManager enma = JPAConfig_24162103.getEntityManager();
        try {
            return enma.find(Category_24162103.class, categoryId);
        } finally {
            enma.close();
        }
    }

    public long countVideosByCategory(int categoryId) {
        EntityManager enma = JPAConfig_24162103.getEntityManager();
        try {
            TypedQuery<Long> query = enma.createQuery(
                    "SELECT COUNT(v) FROM Video_24162103 v WHERE v.categoryId = :cid", Long.class);
            query.setParameter("cid", categoryId);
            return query.getSingleResult();
        } finally {
            enma.close();
        }
    }
}
