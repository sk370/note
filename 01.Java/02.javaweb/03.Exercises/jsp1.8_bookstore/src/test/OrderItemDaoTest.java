package test;

import dao.OrderItemDao;
import dao.impl.OrderItemDaoImpl;
import org.junit.jupiter.api.Test;
import native_.pojo.OrderItem;

import java.math.BigDecimal;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/7/11 10:31
 */
class OrderItemDaoTest {

    @Test
    void saveOrderItem() {
        OrderItemDao orderItemDao = new OrderItemDaoImpl();
        orderItemDao.saveOrderItem(new OrderItem(null, "侠客行", 1, new BigDecimal(100), new BigDecimal("100"), "123456789"));
        orderItemDao.saveOrderItem(new OrderItem(null, "连城诀", 1, new BigDecimal(100), new BigDecimal("100"), "123456789"));
        orderItemDao.saveOrderItem(new OrderItem(null, "倚天屠龙记", 1, new BigDecimal(100), new BigDecimal("100"), "123456789"));
    }
}