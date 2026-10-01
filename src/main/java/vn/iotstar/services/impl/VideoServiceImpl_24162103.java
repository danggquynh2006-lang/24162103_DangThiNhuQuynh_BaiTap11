package vn.iotstar.services.impl;

import java.util.List;
import vn.iotstar.dao.VideoDao_24162103;
import vn.iotstar.entity.Video_24162103;
import vn.iotstar.services.IVideoService_24162103;

public class VideoServiceImpl_24162103 implements IVideoService_24162103 {
    private VideoDao_24162103 videoDao = new VideoDao_24162103();

    @Override
    public void insert(Video_24162103 video) {
        videoDao.insert(video);
    }

    @Override
    public void update(Video_24162103 video) {
        videoDao.update(video);
    }

    @Override
    public void delete(String videoId) {
        videoDao.delete(videoId);
    }

    @Override
    public Video_24162103 findById(String videoId) {
        return videoDao.findById(videoId);
    }

    @Override
    public List<Video_24162103> findAll(int page, int pageSize) {
        return videoDao.findAll(page, pageSize);
    }

    @Override
    public long countAll() {
        return videoDao.countAll();
    }

    @Override
    public List<Video_24162103> findByCategory(int categoryId, int page, int pageSize) {
        return videoDao.findByCategory(categoryId, page, pageSize);
    }

    @Override
    public long countByCategory(int categoryId) {
        return videoDao.countByCategory(categoryId);
    }
}
