package iceriver.boot;

import iceriver.boot.bean.Pet;
import iceriver.boot.bean.User;
import iceriver.boot.config.MyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/8/3 20:10
 */
//@SpringBootApplication(scanBasePackages = "iceriver.boot")
@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        //1、返回IOC容器
        ConfigurableApplicationContext run = SpringApplication.run(MainApplication.class, args);
        //2、查看容器里面的组件
        String[] names = run.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }
        //3、从容器获取组件
        Pet tom01 = run.getBean("tom", Pet.class);
        Pet tom02 = run.getBean("tom", Pet.class);
        System.out.println(tom01 == tom02);//true

        MyConfig bean = run.getBean(MyConfig.class);
        //System.out.println(bean);//iceriver.boot.config.MyConfig$$EnhancerBySpringCGLIB$$2bce5da6@585ac855
        User user = bean.user01();
        User user1 = bean.user01();
        System.out.println(user == user1);//true
    }
}
