package vn.iotstar.controllers;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.iotstar.entity.Order_24162103;
import vn.iotstar.entity.User_24162103;
import vn.iotstar.services.IOrderService_24162103;
import vn.iotstar.services.impl.OrderServiceImpl_24162103;

@WebServlet("/orders")
public class OrderHistoryController_24162103 extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IOrderService_24162103 orderService = new OrderServiceImpl_24162103();

    // Danh sach trang thai co dinh de hien thi nut loc, dung thu tu quy trinh don hang
    public static final String[] STATUS_LIST = {
        "Đơn hàng mới", "Đã xác nhận", "Chuẩn bị hàng", "Vận chuyển",
        "Giao hàng", "Đã giao", "Đơn hàng hủy", "Đơn hàng hoàn"
    };

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

        String status = req.getParameter("status"); // null hoac rong = "Tat ca"
        List<Order_24162103> orders = orderService.getOrderHistory(username, status);

        req.setAttribute("orders", orders);
        req.setAttribute("statusList", STATUS_LIST);
        req.setAttribute("selectedStatus", status);
        req.getRequestDispatcher("/views/user/order-history.jsp").forward(req, resp);
    }
}