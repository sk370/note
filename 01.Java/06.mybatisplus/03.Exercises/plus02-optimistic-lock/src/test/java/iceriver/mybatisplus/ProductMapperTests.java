package iceriver.mybatisplus;

import iceriver.mybatisplus.bean.Product;
import iceriver.mybatisplus.mapper.ProductMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/8/5 14:49
 */
@SpringBootTest
public class ProductMapperTests {
    @Autowired
    private ProductMapper productMapper;
    @Test
    public void  test(){
        //1. 小李查询商品价格
        Product productL = productMapper.selectById(1);
        System.out.println("小李查询的商品价格" + productL.getPrice());
        //2. 小王查询商品价格
        Product productW = productMapper.selectById(1);
        System.out.println("小王查询的商品价格" + productW.getPrice());
        System.out.println("----------修改价格前------------");
        //3. 小李对商品价格+50
        productL.setPrice(productL.getPrice() + 50);
        productMapper.updateById(productL);
        //4. 小王对商品价格-30
        System.out.println("小王查询的商品价格" + productW.getPrice());
        productW.setPrice(productW.getPrice() - 30);
        productMapper.updateById(productL);
        System.out.println("----------修改价格后------------");
        //5. 老板检查价格
        Product productB = productMapper.selectById(1);
        System.out.println("老板查询的商品价格" + productB.getPrice());
    }
    @Test
    public void  test02(){
        //1. 小李查询商品价格
        Product productL = productMapper.selectById(1);
        System.out.println("小李查询的商品价格" + productL.getPrice());
        //2. 小王查询商品价格
        Product productW = productMapper.selectById(1);
        System.out.println("小王查询的商品价格" + productW.getPrice());
        System.out.println("----------修改价格前------------");
        //3. 小李对商品价格+50
        productL.setPrice(productL.getPrice() + 50);
        productMapper.updateById(productL);
        //4. 小王对商品价格-30
        System.out.println("小王查询的商品价格" + productW.getPrice());
        productW.setPrice(productW.getPrice() - 30);

        int result = productMapper.updateById(productW);
        if(result == 0){
            Product product = productMapper.selectById(1);
            product.setPrice(product.getPrice() - 30);
            productMapper.updateById(product);
        }

        productMapper.updateById(productL);
        System.out.println("----------修改价格后------------");
        //5. 老板检查价格
        Product productB = productMapper.selectById(1);
        System.out.println("老板查询的商品价格" + productB.getPrice());
    }
}
