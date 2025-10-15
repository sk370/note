package iceriver.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @className EurakeMain7001
 * @description
 * @author {USER}
 * @date 2022/08/22 15:11
 * @version v2.0
 */
@SpringBootApplication
@EnableEurekaServer
public class EurakeMain7002 {
    public static void main(String[] args) {
        SpringApplication.run(EurakeMain7002.class, args);
    }
}
