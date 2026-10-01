package vn.iotstar.services;

import java.util.List;
import vn.iotstar.entity.Video_24162103;

public interface IVideoService_24162103 {
    void insert(Video_24162103 video);
    void update(Video_24162103 video);
    void delete(String videoId);
    Video_24162103 findById(String videoId);

    List<Video_24162103> findAll(int page, int pageSize);
    long countAll();

    List<Video_24162103> findByCategory(int categoryId, int page, int pageSize);
    long countByCategory(int categoryId);
}
