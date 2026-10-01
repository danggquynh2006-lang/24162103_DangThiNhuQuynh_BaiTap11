package vn.iotstar.services.impl;

import vn.iotstar.dao.FavoriteDao_24162103;
import vn.iotstar.services.IFavoriteService_24162103;

public class FavoriteServiceImpl_24162103 implements IFavoriteService_24162103 {
    private FavoriteDao_24162103 favoriteDao = new FavoriteDao_24162103();

    @Override
    public long countByVideoId(String videoId) {
        return favoriteDao.countByVideoId(videoId);
    }
}
