package iceriver.springcloud.service;

import iceriver.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/8/19 9:33
 */
public interface PaymentService {
    int create(Payment payment);
    Payment getPaymentById(@Param("id") Long id);
}
