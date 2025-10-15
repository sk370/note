package iceriver.mybatis.mapper;

import iceriver.mybatis.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/7/26 7:22
 */
public interface UserMapper {
    /**
     * 添加用户
     * @return 受影响的行数
     */
    int insertUser();

    /**
     * 修改用户
     */
    void updateUser();

    /**
     * 删除用户
     */
    void deleteUser();

    /**
     * 根据用户id查找用户
     * @return
     */
    User getUserById();

    /**
     * 查询所有用户
     * @return
     */
    List<User> getAllUser();

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    User getUserByUsername(String username);

    /**
     * 登录验证
     * @param username
     * @param password
     * @return
     */
    User checkLogin(String username, String password);

    /**
     * 登录验证（参数map）
     * @param map
     * @return
     */
    User checkLoginByMap(Map<String, Object> map);
    /**
     * 登录验证（参数实体类）
     * @param user
     * @return
     */
    User checkLoginByUser(User user);
    /**
     * 登录验证（使用@param）
     * @param username
     * @param password
     * @return
     */
    User checkLoginByParams(@Param("username") String username, @Param("password") String password);
}

