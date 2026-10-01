package vn.iotstar.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

import vn.iotstar.entity.User_24162103;

/**
 * Chan truy cap /admin/* neu chua dang nhap hoac dang nhap khong phai
 * Admin (dung boi sung cho yeu cau "Admin moi co chuc nang nay" - Cau 1).
 */
@WebFilter(urlPatterns = {"/admin/*"})
public class AdminAuthFilter_24162103 implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);
        Object account = (session != null) ? session.getAttribute("account") : null;

        boolean isAdmin = (account instanceof User_24162103) && ((User_24162103) account).isAdmin();

        if (!isAdmin) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        chain.doFilter(request, response);
    }
}
