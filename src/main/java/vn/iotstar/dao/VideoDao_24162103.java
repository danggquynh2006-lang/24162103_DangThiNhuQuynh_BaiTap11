package vn.iotstar.dao;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import vn.iotstar.configs.JPAConfig_24162103;
import vn.iotstar.entity.Video_24162103;

public class VideoDao_24162103 {

    public void insert(Video_24162103 video) {
        EntityManager enma = JPAConfig_24162103.getEntityManager();
        try {
            enma.getTransaction().begin();
            enma.persist(video);
            enma.getTransaction().commit();
        } catch (Exception e) {
            enma.getTransaction().rollback();
            throw e;
        } finally {
            enma.close();
        }
    }

    public void update(Video_24162103 video) {
        EntityManager enma = JPAConfig_24162103.getEntityManager();
        try {
            enma.getTransaction().begin();
            enma.merge(video);
            enma.getTransaction().commit();
        } catch (Exception e) {
            enma.getTransaction().rollback();
            throw e;
        } finally {
            enma.close();
        }
    }

    public void delete(String videoId) {
        EntityManager enma = JPAConfig_24162103.getEntityManager();
        try {
            enma.getTransaction().begin();
            enma.createQuery("DELETE FROM Share_24162103 s WHERE s.videoId = :vid")
                .setParameter("vid", videoId).executeUpdate();
            enma.createQuery("DELETE FROM Favorite_24162103 f WHERE f.videoId = :vid")
                .setParameter("vid", videoId).executeUpdate();
            Video_24162103 video = enma.find(Video_24162103.class, videoId);
            if (video != null) {
                enma.remove(video);
            }
            enma.getTransaction().commit();
        } catch (Exception e) {
            enma.getTransaction().rollback();
            throw e;
        } finally {
            enma.close();
        }
    }

    public Video_24162103 findById(String videoId) {
        EntityManager enma = JPAConfig_24162103.getEntityManager();
        try {
            return enma.find(Video_24162103.class, videoId);
        } finally {
            enma.close();
        }
    }

    public List<Video_24162103> findAll(int page, int pageSize) {
        EntityManager enma = JPAConfig_24162103.getEntityManager();
        try {
            TypedQuery<Video_24162103> query = enma.createQuery(
                    "SELECT v FROM Video_24162103 v ORDER BY v.videoId", Video_24162103.class);
            query.setFirstResult((page - 1) * pageSize);
            query.setMaxResults(pageSize);
            return query.getResultList();
        } finally {
            enma.close();
        }
    }

    public long countAll() {
        EntityManager enma = JPAConfig_24162103.getEntityManager();
        try {
            TypedQuery<Long> query = enma.createQuery("SELECT COUNT(v) FROM Video_24162103 v", Long.class);
            return query.getSingleResult();
        } finally {
            enma.close();
        }
    }

    public List<Video_24162103> findByCategory(int categoryId, int page, int pageSize) {
        EntityManager enma = JPAConfig_24162103.getEntityManager();
        try {
            TypedQuery<Video_24162103> query = enma.createQuery(
                    "SELECT v FROM Video_24162103 v WHERE v.categoryId = :cid ORDER BY v.videoId", Video_24162103.class);
            query.setParameter("cid", categoryId);
            query.setFirstResult((page - 1) * pageSize);
            query.setMaxResults(pageSize);
            return query.getResultList();
        } finally {
            enma.close();
        }
    }

    public long countByCategory(int categoryId) {
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
