package iceriver.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("iceriver.mybatisplus.mapper")
public class Plus02OptimisticLockApplication {

    public static void main(String[] args) {
        SpringApplication.run(Plus02OptimisticLockApplication.class, args);
    }

}
