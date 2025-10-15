package service;

import native_.pojo.Cart;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/7/11 10:58
 */
public interface OrderService {
    String createOrder(Cart cart, Integer userId);
}
