package vn.iotstar.services;

import java.util.List;
import vn.iotstar.entity.Cart_24162103;

public interface ICartService_24162103 {
    List<Cart_24162103> getCartByUsername(String username);
    void addToCart(String username, String videoId, int quantity);
    void updateQuantity(Integer cartId, int quantity);
    void removeFromCart(Integer cartId);
    void clearCart(String username);
    double getCartTotal(String username);
}