package iceriver.springcloud.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhuyuqi
 * @version v0.0.1
 * @className PaymentController
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/08/23 09:16
 */
@RestController
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/consul")
    public String paymentInfo() {
        return "springcloud with consul: " + serverPort + "\t\t" + UUID.randomUUID().toString();
    }
}
