package vn.iotstar.controllers;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.iotstar.entity.Cart_24162103;
import vn.iotstar.entity.User_24162103;
import vn.iotstar.services.ICartService_24162103;
import vn.iotstar.services.impl.CartServiceImpl_24162103;

@WebServlet("/cart")
public class CartController_24162103 extends HttpServlet {
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
        String username = account.getUsername();

        List<Cart_24162103> cartItems = cartService.getCartByUsername(username);
        double total = cartService.getCartTotal(username);

        req.setAttribute("cartItems", cartItems);
        req.setAttribute("cartTotal", total);
        req.getRequestDispatcher("/views/user/cart.jsp").forward(req, resp);
    }
}