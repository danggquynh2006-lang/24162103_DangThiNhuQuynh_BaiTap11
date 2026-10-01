package vn.iotstar.services.impl;

import java.util.Date;
import java.util.List;
import vn.iotstar.dao.CartDao_24162103;
import vn.iotstar.entity.Cart_24162103;
import vn.iotstar.services.ICartService_24162103;

public class CartServiceImpl_24162103 implements ICartService_24162103 {

    private CartDao_24162103 cartDao = new CartDao_24162103();

    public static final int MAX_QUANTITY = 10; // gioi han so luong 1 san pham
    public static final int MIN_QUANTITY = 1;

    @Override
    public List<Cart_24162103> getCartByUsername(String username) {
        return cartDao.findByUsername(username);
    }

    @Override
    public void addToCart(String username, String videoId, int quantity) {
        if (quantity < MIN_QUANTITY) quantity = MIN_QUANTITY;
        if (quantity > MAX_QUANTITY) quantity = MAX_QUANTITY;

        Cart_24162103 existing = cartDao.findByUsernameAndVideoId(username, videoId);
        if (existing != null) {
            int newQty = existing.getQuantity() + quantity;
            if (newQty > MAX_QUANTITY) newQty = MAX_QUANTITY;
            cartDao.updateQuantity(existing.getCartId(), newQty);
        } else {
            Cart_24162103 cart = new Cart_24162103();
            cart.setUsername(username);
            cart.setVideoId(videoId);
            cart.setQuantity(quantity);
            cart.setCreatedDate(new Date());
            cartDao.insert(cart);
        }
    }

    @Override
    public void updateQuantity(Integer cartId, int quantity) {
        if (quantity < MIN_QUANTITY) quantity = MIN_QUANTITY;
        if (quantity > MAX_QUANTITY) quantity = MAX_QUANTITY;
        cartDao.updateQuantity(cartId, quantity);
    }

    @Override
    public void removeFromCart(Integer cartId) {
        cartDao.delete(cartId);
    }

    @Override
    public void clearCart(String username) {
        cartDao.deleteAllByUsername(username);
    }

    @Override
    public double getCartTotal(String username) {
        double total = 0;
        for (Cart_24162103 c : cartDao.findByUsername(username)) {
            total += c.getSubTotal();
        }
        return total;
    }
}