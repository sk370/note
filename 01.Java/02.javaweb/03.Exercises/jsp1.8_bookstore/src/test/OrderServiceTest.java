package test;

import org.junit.jupiter.api.Test;
import native_.pojo.Cart;
import native_.pojo.CartItem;
import service.OrderService;
import service.impl.OrderServiceImpl;

import java.math.BigDecimal;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/7/11 11:54
 */
class OrderServiceTest {

    @Test
    void createOrder() {
//      生成购物车
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "茶馆", 1, new BigDecimal(120), new BigDecimal(120),10));
        cart.addItem(new CartItem(1, "茶馆", 1, new BigDecimal(120), new BigDecimal(120),10));
        cart.addItem(new CartItem(2, "茶馆", 1, new BigDecimal(120), new BigDecimal(120),10));

        OrderService orderService = new OrderServiceImpl();
        System.out.println(orderService.createOrder(cart,1));
    }
}