package iceriver.mybatisplus;

import iceriver.mybatisplus.bean.Product;
import iceriver.mybatisplus.mapper.ProductMapper;
import iceriver.mybatisplus.mapper.UserMapper;
import iceriver.mybatisplus.service.ProductService;
import iceriver.mybatisplus.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Plus05DatasourcesApplicationTests {
    @Test
    void contextLoads() {
    }
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Test
    public void test(){
        System.out.println(userService.getById(1));
        System.out.println(productService.getById(1));
    }
}
