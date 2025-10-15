package iceriver.springcloud.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import iceriver.springcloud.entities.CommonResult;
import iceriver.springcloud.entities.Payment;
import iceriver.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/8/19 9:36
 */
@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Value("${server.port}")
    private String port;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("*****插入结果：" + result);
        if (result > 0) {
            return new CommonResult(200, "插入数据库成功" + port, null);
        } else {
            return new CommonResult(444, "插入数据库失败" + port, null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("*****查询结果：" + payment);
        if (payment != null) {
            return new CommonResult(200, "查询成功" + port, payment);
        } else {
            return new CommonResult(444, "查询失败" + port, null);
        }
    }

    @GetMapping("/payment/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("*******service:" + service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PROVIDER-PAYMENT");
        for (ServiceInstance instance : instances) {
            log.info("*******instance:" + instance.getInstanceId() + "\t" + instance.getHost() + "\t"
                + instance.getPort() + "\t" + instance.getUri());
        }
        return this.discoveryClient;
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB() {
        return port;
    }
}
