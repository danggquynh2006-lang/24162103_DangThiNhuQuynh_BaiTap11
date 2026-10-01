package vn.iotstar.services;

import vn.iotstar.entity.Order_24162103;

public interface IOrderService_24162103 {
    Order_24162103 placeOrder(String username, String fullName, String phone, String address);
}