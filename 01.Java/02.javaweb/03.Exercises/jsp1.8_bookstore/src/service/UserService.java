package service;

import native_.pojo.User;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/7/3 18:56
 */
public interface UserService {
    /**
     * 注册
     * @param user
     */
    void registUser(User user);

    /**
     * 登录
     * @param user
     * @return 登录成功返回该User对象，失败返回null
     */
    User login(User user);

    /**
     * 检查用户名是否可用
     * @param username
     * @return true表示用户名已存在，false表示用户名可用
     */
    boolean existUsername(String username);

}
