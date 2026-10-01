package vn.iotstar.services;

import java.util.List;
import vn.iotstar.entity.Order_24162103;

public interface IOrderService_24162103 {
    Order_24162103 placeOrder(String username, String fullName, String phone, String address);
    List<Order_24162103> getOrderHistory(String username, String status);
    Order_24162103 getOrderDetail(Integer orderId, String username);
}