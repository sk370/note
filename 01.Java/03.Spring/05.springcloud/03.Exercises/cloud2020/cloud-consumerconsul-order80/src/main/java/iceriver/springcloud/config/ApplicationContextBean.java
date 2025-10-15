package iceriver.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhuyuqi
 * @version v0.0.1
 * @className ApplicationContextBean
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/08/23 08:40
 */
@Configuration
public class ApplicationContextBean {
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
