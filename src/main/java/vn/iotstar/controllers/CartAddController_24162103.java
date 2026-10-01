package vn.iotstar.controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.iotstar.entity.User_24162103;
import vn.iotstar.services.ICartService_24162103;
import vn.iotstar.services.impl.CartServiceImpl_24162103;

@WebServlet("/cart/add")
public class CartAddController_24162103 extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ICartService_24162103 cartService = new CartServiceImpl_24162103();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        User_24162103 account = (User_24162103) session.getAttribute("account");
        if (account == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        String username = account.getUsername();

        String videoId = req.getParameter("videoId");
        int quantity;
        try {
            quantity = Integer.parseInt(req.getParameter("quantity"));
        } catch (Exception e) {
            quantity = 1;
        }

        cartService.addToCart(username, videoId, quantity);
        resp.sendRedirect(req.getContextPath() + "/cart");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }
}