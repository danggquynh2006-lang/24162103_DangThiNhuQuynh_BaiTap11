package vn.iotstar.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

import vn.iotstar.entity.Video_24162103;
import vn.iotstar.services.IVideoService_24162103;
import vn.iotstar.services.impl.VideoServiceImpl_24162103;

/**
 * Quan tri danh sach Video - phan trang 6 video / trang (Cau 2 - CRUD).
 */
@WebServlet(urlPatterns = {"/admin/videos"})
public class AdminVideoController_24162103 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final int PAGE_SIZE = 6;

    private IVideoService_24162103 videoService = new VideoServiceImpl_24162103();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        int page = 1;
        String pageParam = req.getParameter("page");
        if (pageParam != null && !pageParam.isEmpty()) {
            try {
                page = Integer.parseInt(pageParam);
                if (page < 1) page = 1;
            } catch (NumberFormatException ignore) {
                page = 1;
            }
        }

        try {
            long totalVideo = videoService.countAll();
            int totalPages = (int) Math.ceil(totalVideo / (double) PAGE_SIZE);
            if (totalPages < 1) totalPages = 1;
            if (page > totalPages) page = totalPages;

            List<Video_24162103> list = videoService.findAll(page, PAGE_SIZE);

            req.setAttribute("listVideo", list);
            req.setAttribute("currentPage", page);
            req.setAttribute("totalPages", totalPages);
            req.setAttribute("totalVideo", totalVideo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        req.getRequestDispatcher("/views/admin/video-list.jsp").forward(req, resp);
    }
}
