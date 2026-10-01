package vn.iotstar.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import vn.iotstar.configs.JPAConfig_24162103;

public class ShareDao_24162103 {
    public long countByVideoId(String videoId) {
        EntityManager enma = JPAConfig_24162103.getEntityManager();
        try {
            TypedQuery<Long> query = enma.createQuery(
                    "SELECT COUNT(s) FROM Share_24162103 s WHERE s.videoId = :vid", Long.class);
            query.setParameter("vid", videoId);
            return query.getSingleResult();
        } finally {
            enma.close();
        }
    }
}
