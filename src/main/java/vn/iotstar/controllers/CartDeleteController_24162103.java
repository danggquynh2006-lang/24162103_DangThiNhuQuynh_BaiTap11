package vn.iotstar.controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.iotstar.entity.User_24162103;
import vn.iotstar.services.ICartService_24162103;
import vn.iotstar.services.impl.CartServiceImpl_24162103;

@WebServlet("/cart/delete")
public class CartDeleteController_24162103 extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ICartService_24162103 cartService = new CartServiceImpl_24162103();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        User_24162103 account = (User_24162103) session.getAttribute("account");
        if (account == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        try {
            Integer cartId = Integer.parseInt(req.getParameter("cartId"));
            cartService.removeFromCart(cartId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + "/cart");
    }
}