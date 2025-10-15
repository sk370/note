package iceriver.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zhuyuqi
 * @version v0.0.1
 * @className OrderConsul80
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/08/23 08:39
 */
@SpringBootApplication
@EnableDiscoveryClient
public class OrderConsul80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderConsul80.class, args);
    }
}
