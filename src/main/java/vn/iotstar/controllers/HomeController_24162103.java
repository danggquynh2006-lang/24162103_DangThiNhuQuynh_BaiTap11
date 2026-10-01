package vn.iotstar.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import vn.iotstar.dto.CategoryBlock_24162103;
import vn.iotstar.entity.Category_24162103;
import vn.iotstar.entity.Video_24162103;
import vn.iotstar.services.ICategoryService_24162103;
import vn.iotstar.services.IFavoriteService_24162103;
import vn.iotstar.services.IShareService_24162103;
import vn.iotstar.services.impl.FavoriteServiceImpl_24162103;
import vn.iotstar.services.impl.ShareServiceImpl_24162103;
import vn.iotstar.services.IVideoService_24162103;
import vn.iotstar.services.impl.CategoryServiceImpl_24162103;
import vn.iotstar.services.impl.VideoServiceImpl_24162103;

/**
 * Trang chu cua vai tro User: hien thi tat ca video theo tung Category,
 * moi Category phan trang rieng 3 video/trang (Cau 4), kem so luong video
 * cua tung Category (Cau 5).
 */
@WebServlet(urlPatterns = {"/home"})
public class HomeController_24162103 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final int PAGE_SIZE = 3;

    private IVideoService_24162103 videoService = new VideoServiceImpl_24162103();
    private ICategoryService_24162103 categoryService = new CategoryServiceImpl_24162103();
    private IShareService_24162103 shareService = new ShareServiceImpl_24162103();
    private IFavoriteService_24162103 favoriteService = new FavoriteServiceImpl_24162103();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        List<CategoryBlock_24162103> blocks = new ArrayList<>();

        try {
            List<Category_24162103> categories = categoryService.findAll();

            for (Category_24162103 cat : categories) {
                int categoryId = cat.getCategoryId();

                // Moi category co tham so phan trang rieng: page_<categoryId>
                int page = 1;
                String pageParam = req.getParameter("page_" + categoryId);
                if (pageParam != null && !pageParam.isEmpty()) {
                    try {
                        page = Integer.parseInt(pageParam);
                        if (page < 1) page = 1;
                    } catch (NumberFormatException ignore) {
                        page = 1;
                    }
                }

                long totalVideo = videoService.countByCategory(categoryId);
                int totalPages = (int) Math.ceil(totalVideo / (double) PAGE_SIZE);
                if (totalPages < 1) totalPages = 1;
                if (page > totalPages) page = totalPages;

                List<Video_24162103> videos = videoService.findByCategory(categoryId, page, PAGE_SIZE);

                for (Video_24162103 v : videos) {
                    v.setShareCount(shareService.countByVideoId(v.getVideoId()));
                    v.setLikeCount(favoriteService.countByVideoId(v.getVideoId()));
                }

                // Chi hien thi cac category co it nhat 1 video
                if (totalVideo > 0) {
                    CategoryBlock_24162103 block = new CategoryBlock_24162103();
                    block.setCategoryId(categoryId);
                    block.setCategoryName(cat.getCategoryName());
                    block.setVideos(videos);
                    block.setCurrentPage(page);
                    block.setTotalPages(totalPages);
                    block.setTotalVideo(totalVideo);
                    blocks.add(block);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        req.setAttribute("categoryBlocks", blocks);
        req.getRequestDispatcher("/views/user/home.jsp").forward(req, resp);
    }
}
