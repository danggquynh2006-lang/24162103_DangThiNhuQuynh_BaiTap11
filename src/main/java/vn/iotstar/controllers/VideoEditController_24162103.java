package vn.iotstar.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

import vn.iotstar.entity.Category_24162103;
import vn.iotstar.entity.Video_24162103;
import vn.iotstar.services.ICategoryService_24162103;
import vn.iotstar.services.IVideoService_24162103;
import vn.iotstar.services.impl.CategoryServiceImpl_24162103;
import vn.iotstar.services.impl.VideoServiceImpl_24162103;

@WebServlet(urlPatterns = {"/admin/video/edit"})
@MultipartConfig(maxFileSize = 5 * 1024 * 1024)
public class VideoEditController_24162103 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private IVideoService_24162103 videoService = new VideoServiceImpl_24162103();
    private ICategoryService_24162103 categoryService = new CategoryServiceImpl_24162103();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        try {
            // Truoc day cho nay khong lay du lieu that, khien form Sua luon
            // hien thi du lieu gia. Bay gio lay dung video theo id truyen len.
            Video_24162103 video = videoService.findById(id);
            List<Category_24162103> categories = categoryService.findAll();

            req.setAttribute("video", video);
            req.setAttribute("categories", categories);
        } catch (Exception e) {
            e.printStackTrace();
        }

        req.getRequestDispatcher("/views/admin/video-edit.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        try {
            String videoId = req.getParameter("videoId");
            String title = req.getParameter("title");
            String description = req.getParameter("description");
            String poster = req.getParameter("poster");

            String viewsStr = req.getParameter("views");
            int views = (viewsStr != null && !viewsStr.isEmpty()) ? Integer.parseInt(viewsStr) : 0;

            String categoryIdStr = req.getParameter("categoryId");
            int categoryId = (categoryIdStr != null && !categoryIdStr.isEmpty()) ? Integer.parseInt(categoryIdStr) : 0;

            String activeStr = req.getParameter("active");
            boolean active = "on".equals(activeStr) || "true".equals(activeStr);

            // Lay ban ghi hien co de giu lai poster cu neu khong doi anh moi
            Video_24162103 video = videoService.findById(videoId);
            if (video == null) {
                video = new Video_24162103();
                video.setVideoId(videoId);
            }
            video.setTitle(title);
            video.setDescription(description);
            video.setViews(views);
            video.setCategoryId(categoryId);
            video.setActive(active);
            if (poster != null && !poster.isEmpty()) {
                video.setPoster(poster);
            }

            // Truoc day cho nay khong goi update() nen Sua khong co tac dung.
            videoService.update(video);

        } catch (Exception e) {
            e.printStackTrace();
        }

        resp.sendRedirect(req.getContextPath() + "/admin/videos");
    }
}
