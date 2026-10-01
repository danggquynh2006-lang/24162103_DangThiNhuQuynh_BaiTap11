package vn.iotstar.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.Random;
import vn.iotstar.entity.User_24162103;
import vn.iotstar.services.IUserService_24162103;
import vn.iotstar.services.impl.UserServiceImpl_24162103;
import vn.iotstar.utils.EmailUtils_24162103;

@WebServlet(urlPatterns = {"/register"})
public class RegisterController_24162103 extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IUserService_24162103 userService = new UserServiceImpl_24162103();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        if (userService.exists(username)) {
            req.setAttribute("error", "Tài khoản đã tồn tại!");
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
            return;
        }

        // Tạo đối tượng User tạm thời
        User_24162103 user = new User_24162103();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setActive(false); // Chưa kích hoạt cho đến khi nhập đúng OTP
        user.setAdmin(false);

        // Sinh mã OTP ngẫu nhiên 6 chữ số
        String otpCode = String.format("%06d", new Random().nextInt(999999));

        // Lưu thông tin user và mã OTP vào HttpSession để sang trang sau kiểm tra
        HttpSession session = req.getSession();
        session.setAttribute("userReg", user);
        session.setAttribute("otpCode", otpCode);

        // Gửi mã OTP qua email thực tế
        try {
            EmailUtils_24162103.sendOtp(email, otpCode);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Chuyển hướng sang trang nhập mã OTP xác thực
        resp.sendRedirect(req.getContextPath() + "/verify");
    }
}