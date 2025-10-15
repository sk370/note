package service.impl;

import dao.BookDao;
import dao.OrderDao;
import dao.OrderItemDao;
import dao.impl.BookDaoImpl;
import dao.impl.OrderDaoImpl;
import dao.impl.OrderItemDaoImpl;
import native_.pojo.*;
import service.OrderService;

import java.util.Date;
import java.util.Map;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/7/11 10:59
 */
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public String createOrder(Cart cart, Integer userId) {//清空购物车式结账，所以参数是cart
        String orderId = System.currentTimeMillis() + "" + userId;//创建唯一的订单号
        Order order = new Order(orderId, new Date(), cart.getTotalPrice(), 0, userId);//生成包含一大堆订单的订单列表
        orderDao.saveOrder(order);//把订单列表保存到数据库

//        遍历购物车中每一个商品项，作为订单项的每一项进行保存
        for(Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()){
            CartItem cartItem = entry.getValue();
            OrderItem orderItem = new OrderItem(cartItem.getId(), cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(),orderId);//把购物车的商品添加到订单
            orderItemDao.saveOrderItem(orderItem);//把订单保存到数据库

//            更新首页库存及销量
            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales(book.getSales() + cartItem.getCount());
            book.setStock(book.getStock() - cartItem.getStock());
            bookDao.updateBook(book);

        }
        cart.clear();//清空购物车
        return orderId;
    }
}
