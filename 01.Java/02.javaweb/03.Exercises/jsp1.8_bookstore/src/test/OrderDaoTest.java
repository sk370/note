package test;

import dao.OrderDao;
import dao.impl.OrderDaoImpl;
import org.junit.jupiter.api.Test;
import native_.pojo.Order;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/7/11 10:33
 */
public class OrderDaoTest {
    @Test
    void saveOrder(){
        OrderDao orderDao = new OrderDaoImpl();
        orderDao.saveOrder(new Order("123456789", new Date(), new BigDecimal(100), 0, 1));

    }
}
