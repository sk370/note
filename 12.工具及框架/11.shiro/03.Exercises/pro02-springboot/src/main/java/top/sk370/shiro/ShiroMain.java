package top.sk370.shiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhuyuqi
 * @version v0.0.1
 * @className ShiroMain
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/10/01 17:55
 */
@SpringBootApplication
@MapperScan("top.sk370.shiro.mapper")
public class ShiroMain {
    public static void main(String[] args) {
        SpringApplication.run(ShiroMain.class, args);
    }
}
