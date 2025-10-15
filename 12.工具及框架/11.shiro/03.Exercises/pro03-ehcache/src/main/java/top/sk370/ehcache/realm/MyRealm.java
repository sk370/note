package top.sk370.ehcache.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.sk370.ehcache.entity.User;
import top.sk370.ehcache.service.UserService;

import java.util.List;

/**
 *
 * @author zhuyuqi
 * @version v0.0.1
 * @className MyRealm
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/10/01 19:11
 */
@Component
public class MyRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    /**
     * 自定义登录认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //1 获取用户身份信息
        String name = token.getPrincipal().toString();
        //2 调用业务层获取用户信息（数据库中）
        User user = userService.getUserInfoByName(name);
        //3 判断并将数据完成封装
        if (user != null) {
            AuthenticationInfo info = new SimpleAuthenticationInfo(
                    token.getPrincipal(),
                    user.getPwd(),
                    ByteSource.Util.bytes("salt"),
                    token.getPrincipal().toString()
            );
            return info;
        }
        return null;
    }

    /**
     * 自定义授权（角色、权限检查）
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 1. 获取当前用户身份信息
        String principal = principals.getPrimaryPrincipal().toString();
        // 2.1 调用接口方法获取用户的角色信息
        List<String> roles = userService.getUserRoleInfo(principal);
        System.out.println("当前用户角色信息： "+roles);
        // 2.2 调用接口方法获取用户角色的权限信息
        List<String> permissions = userService.getUserPermissionInfo(roles);
        System.out.println("当前用户权限信息： "+permissions);
        // 3. 创建对象，存储当前登录的用户的权限和角色
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 4.1 存储角色
        info.addRoles(roles);
        // 4.2 存储权限信息
        info.addStringPermissions(permissions);
        // 5.返回
        return info;
    }
}
