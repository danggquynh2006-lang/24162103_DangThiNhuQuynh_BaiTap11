package vn.iotstar.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

import vn.iotstar.entity.Video_24162103;
import vn.iotstar.services.IFavoriteService_24162103;
import vn.iotstar.services.IShareService_24162103;
import vn.iotstar.services.IVideoService_24162103;
import vn.iotstar.services.impl.FavoriteServiceImpl_24162103;
import vn.iotstar.services.impl.ShareServiceImpl_24162103;
import vn.iotstar.services.impl.VideoServiceImpl_24162103;

@WebServlet(urlPatterns = {"/video/detail"})
public class VideoDetailController_24162103 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private IVideoService_24162103 videoService = new VideoServiceImpl_24162103();
    private IShareService_24162103 shareService = new ShareServiceImpl_24162103();
    private IFavoriteService_24162103 favoriteService = new FavoriteServiceImpl_24162103();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String id = req.getParameter("id");

        if (id != null) {
            try {
                Video_24162103 video = videoService.findById(id);
                req.setAttribute("video", video);

                // Dem so luot Share / Like that su tu bang Shares / Favorites
                // (truoc day 2 gia tri nay bi hard-code = 10)
                long shareCount = shareService.countByVideoId(id);
                long likeCount = favoriteService.countByVideoId(id);
                req.setAttribute("shareCount", shareCount);
                req.setAttribute("likeCount", likeCount);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        req.getRequestDispatcher("/views/user/video-detail.jsp").forward(req, resp);
    }
}
