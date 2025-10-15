package iceriver.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhuyuqi
 * @version v0.0.1
 * @className OrderConsulController
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/08/23 08:41
 */
@RestController
public class OrderConsulController {

    public static final String INVOKE_URL = "http://consul-provider-payment";

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/consumer/payment/consul")
    public String paymentInfo() {
        String result = restTemplate.getForObject(INVOKE_URL + "/payment/consul", String.class);
        System.out.println("消费者调用支付服务(consul)--->result:" + result);
        return result;
    }
}
