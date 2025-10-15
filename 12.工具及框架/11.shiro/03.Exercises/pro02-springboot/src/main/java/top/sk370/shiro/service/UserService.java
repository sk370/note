package top.sk370.shiro.service;

import org.apache.ibatis.annotations.Param;
import top.sk370.shiro.entity.User;

import java.util.List;

/**
 * @author zhuyuqi
 * @version v0.0.1
 * @className UserService
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/10/01 19:08
 */
public interface UserService {
    //用户登录
    User getUserInfoByName(String name);

    /**
     * 查询数据库中的角色
     * @param principal
     * @return
     */
    List<String> getUserRoleInfo(@Param("principal")String principal );

    /**
     * 查询数据库中角色的权限
     * @param roles
     * @return
     */
    List<String> getUserPermissionInfo(List<String> roles);
}
