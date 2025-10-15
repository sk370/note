package iceriver.mybatisplus.bean;

import lombok.Data;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/8/5 18:24
 */
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
    private Integer sex;
}
