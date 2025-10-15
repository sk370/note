package test;

import org.junit.jupiter.api.Test;
import native_.pojo.Cart;
import native_.pojo.CartItem;

import java.math.BigDecimal;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/7/8 19:27
 */
class CartTest {

    @Test
    void addItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "茶馆", 1, new BigDecimal(120), new BigDecimal(120),10));
        cart.addItem(new CartItem(1, "茶馆", 1, new BigDecimal(120), new BigDecimal(120),10));
        cart.addItem(new CartItem(2, "茶馆", 1, new BigDecimal(120), new BigDecimal(120),10));
        System.out.println(cart);
    }

    @Test
    void updateItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "茶馆", 1, new BigDecimal(120), new BigDecimal(120),10));
        cart.addItem(new CartItem(1, "茶馆", 1, new BigDecimal(120), new BigDecimal(120),10));
        cart.addItem(new CartItem(2, "茶馆", 1, new BigDecimal(120), new BigDecimal(120),10));
        cart.updateItem(2, 5);
        System.out.println(cart);
    }

    @Test
    void deleteItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "茶馆", 1, new BigDecimal(120), new BigDecimal(120),10));
        cart.addItem(new CartItem(1, "茶馆", 1, new BigDecimal(120), new BigDecimal(120),10));
        cart.addItem(new CartItem(2, "茶馆", 1, new BigDecimal(120), new BigDecimal(120),10));
        cart.deleteItem(1);
        System.out.println(cart);
    }

    @Test
    void clear() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "茶馆", 1, new BigDecimal(120), new BigDecimal(120),10));
        cart.addItem(new CartItem(1, "茶馆", 1, new BigDecimal(120), new BigDecimal(120),10));
        cart.addItem(new CartItem(2, "茶馆", 1, new BigDecimal(120), new BigDecimal(120),10));
        cart.clear();
        System.out.println(cart);
    }
}