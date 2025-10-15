package iceriver.boot.config;

import iceriver.boot.bean.Car;
import iceriver.boot.bean.Pet;
import iceriver.boot.bean.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/8/3 23:00
 */
@Configuration(proxyBeanMethods = false)
public class MyConfig {
    @Bean
    public User user01(){
        return new User("张三", 18);
    }
    @Bean("tom")
    public Pet tomPet(){
        return new Pet("tom01");
    }

    @Bean
    public Car car(){
        return new Car();
    }
}
