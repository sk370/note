package utils;

import javax.servlet.http.Cookie;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/7/7 13:44
 */
public class CookieUtils {
    /**
     * 查找指定名称的 Cookie 对象
     * @param name
     * @param cookies
     * @return
     */
    public static Cookie findCookie(String name , Cookie[] cookies){
        if (name == null || cookies == null || cookies.length == 0) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (name.equals(cookie.getName())) {//上面判断的为空的情况，所以不会出现空指针异常
                return cookie;
            }
        }
        return null;
    }
}