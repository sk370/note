package iceriver.mybatis.mapper;

import iceriver.mybatis.pojo.User;

import java.util.List;

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
}

