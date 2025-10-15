package iceriver.mybatisplus.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/8/5 14:47
 */
@TableName("t_product")
@Data
public class Product {
    private Long id;
    private String name;
    private Integer price;
    @Version
    private Integer version;
}
