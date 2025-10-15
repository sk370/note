package iceriver.mybatisplus.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import iceriver.mybatisplus.bean.Product;
import iceriver.mybatisplus.mapper.ProductMapper;
import iceriver.mybatisplus.service.ProductService;
import org.springframework.stereotype.Service;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/8/5 18:39
 */
@Service
@DS("slave_1")
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
}
