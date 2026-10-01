package vn.iotstar.controllers;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.iotstar.entity.Cart_24162103;
import vn.iotstar.entity.Order_24162103;
import vn.iotstar.entity.User_24162103;
import vn.iotstar.services.ICartService_24162103;
import vn.iotstar.services.IOrderService_24162103;
import vn.iotstar.services.impl.CartServiceImpl_24162103;
import vn.iotstar.services.impl.OrderServiceImpl_24162103;

@WebServlet("/checkout")
public class CheckoutController_24162103 extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ICartService_24162103 cartService = new CartServiceImpl_24162103();
    private IOrderService_24162103 orderService = new OrderServiceImpl_24162103();

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
        if (cartItems == null || cartItems.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/cart");
            return;
        }
        req.setAttribute("cartItems", cartItems);
        req.setAttribute("cartTotal", cartService.getCartTotal(username));
        req.getRequestDispatcher("/views/user/checkout.jsp").forward(req, resp);
    }

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

        String fullName = req.getParameter("fullName");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");

        if (fullName == null || fullName.trim().isEmpty()
                || phone == null || phone.trim().isEmpty()
                || address == null || address.trim().isEmpty()) {
            req.setAttribute("error", "Vui lòng nhập đầy đủ Họ tên, SĐT và Địa chỉ nhận hàng.");
            req.setAttribute("cartItems", cartService.getCartByUsername(username));
            req.setAttribute("cartTotal", cartService.getCartTotal(username));
            req.getRequestDispatcher("/views/user/checkout.jsp").forward(req, resp);
            return;
        }

        Order_24162103 order = orderService.placeOrder(username, fullName, phone, address);
        if (order == null) {
            resp.sendRedirect(req.getContextPath() + "/cart");
            return;
        }

        req.setAttribute("order", order);
        req.getRequestDispatcher("/views/user/order-success.jsp").forward(req, resp);
    }
}