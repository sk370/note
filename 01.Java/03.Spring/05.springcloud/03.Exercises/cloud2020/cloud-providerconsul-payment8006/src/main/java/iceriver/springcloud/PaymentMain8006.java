package iceriver.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zhuyuqi
 * @version v0.0.1
 * @className PaymentMain8006
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/08/23 09:14
 */
@SpringBootApplication
@EnableDiscoveryClient
public class PaymentMain8006 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8006.class, args);
    }
}
