package iceriver.myrule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

/**
 * @author zhuyuqi
 * @version v0.0.1
 * @className MySelfRule
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/08/23 10:17
 */
@Configuration
public class MySelfRule {
    @Bean
    public IRule myRule() {
        return new RandomRule();// 定义为随机
    }
}
