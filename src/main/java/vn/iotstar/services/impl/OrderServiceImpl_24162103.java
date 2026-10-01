package vn.iotstar.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import vn.iotstar.dao.CartDao_24162103;
import vn.iotstar.dao.OrderDao_24162103;
import vn.iotstar.entity.Cart_24162103;
import vn.iotstar.entity.Order_24162103;
import vn.iotstar.entity.OrderDetail_24162103;
import vn.iotstar.entity.Video_24162103;
import vn.iotstar.services.IOrderService_24162103;

public class OrderServiceImpl_24162103 implements IOrderService_24162103 {

    private CartDao_24162103 cartDao = new CartDao_24162103();
    private OrderDao_24162103 orderDao = new OrderDao_24162103();

    @Override
    public Order_24162103 placeOrder(String username, String fullName, String phone, String address) {
        List<Cart_24162103> cartItems = cartDao.findByUsername(username);
        if (cartItems == null || cartItems.isEmpty()) {
            return null;
        }

        double total = 0;
        List<OrderDetail_24162103> details = new ArrayList<>();
        for (Cart_24162103 item : cartItems) {
            Video_24162103 video = item.getVideo();
            double price = (video != null && video.getPrice() != null) ? video.getPrice() : 0;

            OrderDetail_24162103 detail = new OrderDetail_24162103();
            detail.setVideoId(item.getVideoId());
            detail.setQuantity(item.getQuantity());
            detail.setPrice(price);
            details.add(detail);

            total += price * item.getQuantity();
        }

        Order_24162103 order = new Order_24162103();
        order.setUsername(username);
        order.setOrderDate(new Date());
        order.setFullName(fullName);
        order.setPhone(phone);
        order.setAddress(address);
        order.setPaymentMethod("COD");
        order.setTotalAmount(total);
        order.setStatus("Chờ xác nhận");

        orderDao.insertOrder(order, details);
        cartDao.deleteAllByUsername(username); // thanh toan xong thi xoa gio hang

        return order;
    }
}