package iceriver.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("iceriver.mybatisplus.mapper")
public class Plus01BaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(Plus01BaseApplication.class, args);
    }

}
