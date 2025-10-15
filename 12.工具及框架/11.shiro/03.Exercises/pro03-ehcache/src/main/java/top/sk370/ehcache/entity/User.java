package top.sk370.ehcache.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhuyuqi
 * @version v0.0.1
 * @className User
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/10/01 19:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String name;
    private String pwd;
    private Integer rid;
}
