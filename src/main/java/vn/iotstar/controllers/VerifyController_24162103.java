package vn.iotstar.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import vn.iotstar.entity.User_24162103;
import vn.iotstar.services.IUserService_24162103;
import vn.iotstar.services.impl.UserServiceImpl_24162103;

@WebServlet(urlPatterns = {"/verify"})
public class VerifyController_24162103 extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IUserService_24162103 userService = new UserServiceImpl_24162103();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Mở trang nhập mã OTP
        req.getRequestDispatcher("/views/verify.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String inputOtp = req.getParameter("otp");

        HttpSession session = req.getSession();
        String sessionOtp = (String) session.getAttribute("otpCode");
        User_24162103 user = (User_24162103) session.getAttribute("userReg");

        if (sessionOtp != null && sessionOtp.equals(inputOtp) && user != null) {
            // Nhập đúng mã OTP -> Lưu user chính thức vào cơ sở dữ liệu
            user.setActive(true);
            userService.register(user);

            // Xóa session tạm
            session.removeAttribute("otpCode");
            session.removeAttribute("userReg");

            // Chuyển hướng về trang đăng nhập kèm thông báo thành công
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            // Nhập sai mã OTP -> Báo lỗi và ở lại trang verify
            req.setAttribute("error", "Mã OTP không chính xác hoặc đã hết hạn!");
            req.getRequestDispatcher("/views/verify.jsp").forward(req, resp);
        }
    }
}