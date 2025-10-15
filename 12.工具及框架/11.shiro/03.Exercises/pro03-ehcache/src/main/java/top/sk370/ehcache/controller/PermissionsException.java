package top.sk370.ehcache.controller;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常管理
 * @author zhuyuqi
 * @version v0.0.1
 * @className PermissionsException
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/10/01 19:18
 */
@ControllerAdvice
public class PermissionsException {
    @ResponseBody
    @ExceptionHandler(UnauthenticatedException.class)
    public String unauthenticatedException(Exception e){
        return "未验证";
    }
    @ResponseBody
    @ExceptionHandler(AuthorizationException.class)
    public String authorizationException(Exception e){
        return "权限认证失败";
    }
}
