package dao;

import native_.pojo.User;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/7/3 16:17
 */
public interface UserDao {

    /**
     * 根据用户名查找用户
     * @param username
     * @return 查到则返回User对象，查不到返回null
     */
    public User queryUserByUsername(String username);

    /**
     * 根据用户名和密码查找用户
     * @param username
     * @param password
     * @return 查到则返回User对象，查不到返回null
     */
    User queryUserByUsernameAndPassword(String username, String password);

    /**
     * 保存用户信息，-1保存失败
     * @param user
     * @return 正数表示注册成功（加入到数据库中），-1表示失败。
     */
    int saveUser(User user);
}
