package vn.iotstar.controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.iotstar.entity.Order_24162103;
import vn.iotstar.entity.User_24162103;
import vn.iotstar.services.IOrderService_24162103;
import vn.iotstar.services.impl.OrderServiceImpl_24162103;

@WebServlet("/order/detail")
public class OrderDetailController_24162103 extends HttpServlet {
    private static final long serialVersionUID = 1L;
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

        Integer orderId;
        try {
            orderId = Integer.parseInt(req.getParameter("id"));
        } catch (Exception e) {
            resp.sendRedirect(req.getContextPath() + "/orders");
            return;
        }

        Order_24162103 order = orderService.getOrderDetail(orderId, username);
        if (order == null) {
            resp.sendRedirect(req.getContextPath() + "/orders");
            return;
        }

        req.setAttribute("order", order);
        req.getRequestDispatcher("/views/user/order-detail.jsp").forward(req, resp);
    }
}