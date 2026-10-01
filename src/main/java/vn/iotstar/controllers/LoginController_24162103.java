package vn.iotstar.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import vn.iotstar.entity.User_24162103;
import vn.iotstar.services.IUserService_24162103;
import vn.iotstar.services.impl.UserServiceImpl_24162103;

@WebServlet(urlPatterns = {"/login"})
public class LoginController_24162103 extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IUserService_24162103 userService = new UserServiceImpl_24162103();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String user = req.getParameter("username");
        String pass = req.getParameter("password");

        User_24162103 account = userService.login(user, pass);
        if (account != null) {
            HttpSession session = req.getSession();
            session.setAttribute("account", account);
            if (account.isAdmin()) {
                resp.sendRedirect(req.getContextPath() + "/admin/videos");
            } else {
                resp.sendRedirect(req.getContextPath() + "/home");
            }
        } else {
            req.setAttribute("error", "Sai tài khoản hoặc mật khẩu!");
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        }
    }
}