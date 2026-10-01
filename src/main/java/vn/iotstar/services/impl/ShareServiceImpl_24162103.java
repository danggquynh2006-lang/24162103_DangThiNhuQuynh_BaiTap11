package vn.iotstar.services.impl;

import vn.iotstar.dao.ShareDao_24162103;
import vn.iotstar.services.IShareService_24162103;

public class ShareServiceImpl_24162103 implements IShareService_24162103 {
    private ShareDao_24162103 shareDao = new ShareDao_24162103();

    @Override
    public long countByVideoId(String videoId) {
        return shareDao.countByVideoId(videoId);
    }
}
