package top.sk370.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;

/**
 * @author zhuyuqi
 * @version v0.0.1
 * @className MyRealm
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/10/01 17:38
 */
public class MyRealm extends AuthenticatingRealm {
    //自定义登录认证方法，shiro的login方法底层会调用该类的认证方法进行认证
    //需要配置自定义的realm生效，在ini文件中配置，在Springboot中配置
    //该方法只是获取进行对比的信息，认证逻辑还是按照shiro底层认证逻辑完成
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //1获取前台的身份信息
        String principal = authenticationToken.getPrincipal().toString();
        //2获取前台的凭证信息
        String password = new String((char[])authenticationToken.getCredentials());
        System.out.println("认证用户信息："+principal+"---"+password);
        //3获取数据库中存储的用户信息——模拟
        if(principal.equals("zhangsan")){
            //3.1数据库中存储的加盐3次迭代的密码
            String pwdInfo = "7174f64b13022acd3c56e2781e098a5f";
            //4创建封装校验逻辑对象，封装数据返回
            AuthenticationInfo info = new SimpleAuthenticationInfo(
                authenticationToken.getPrincipal(),
                pwdInfo,
                ByteSource.Util.bytes("salt"),//md5加密的盐值为salt
                authenticationToken.getPrincipal().toString()
            );
            return info;
        }
        return null;
    }
}
