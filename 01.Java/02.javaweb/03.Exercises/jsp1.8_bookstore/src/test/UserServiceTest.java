package test;

import org.junit.jupiter.api.Test;
import native_.pojo.User;
import service.UserService;
import service.impl.UserServiceImpl;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/7/3 19:14
 */
class UserServiceTest {
    UserService userService = new UserServiceImpl();

    @Test
    void registUser() {
        userService.registUser(new User(null, "user", "user","123124@qq.com"));
    }

    @Test
    void login() {
        userService.login(new User(null, "user", "user","123124@qq.com"));
    }

    @Test
    void existUsername() {
        System.out.println(userService.existUsername("admin"));
    }
}