package iceriver.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

import iceriver.myrule.MySelfRule;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/8/19 11:35
 */
@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "CLOUD-PROVIDER-PAYMENT", configuration = MySelfRule.class)
public class MainApp80 {
    public static void main(String[] args) {
        SpringApplication.run(MainApp80.class, args);
    }
}
