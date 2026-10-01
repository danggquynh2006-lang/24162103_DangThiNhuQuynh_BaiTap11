package vn.iotstar.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

import vn.iotstar.services.IVideoService_24162103;
import vn.iotstar.services.impl.VideoServiceImpl_24162103;

@WebServlet(urlPatterns = {"/admin/video/delete"})
public class VideoDeleteController_24162103 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private IVideoService_24162103 videoService = new VideoServiceImpl_24162103();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        try {
            if (id != null && !id.isEmpty()) {
                // Truoc day cho nay khong goi delete() nen Xoa khong co tac dung.
                videoService.delete(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + "/admin/videos");
    }
}
