package test;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import org.junit.jupiter.api.Test;
import native_.pojo.User;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/7/3 16:43
 */
class UserDaoTest {
    UserDao userDao = new UserDaoImpl();

    @Test
    void queryUserByUsername() {
        System.out.println(userDao.queryUserByUsername("admin"));
    }

    @Test
    void queryUserByUsernameAndPassword() {
        System.out.println(userDao.queryUserByUsernameAndPassword("admin", "admin"));
    }

    @Test
    void saveUser() {
        System.out.println(userDao.saveUser(new User(null, "admin", "admin", "23124122@qq.com")));
    }

}