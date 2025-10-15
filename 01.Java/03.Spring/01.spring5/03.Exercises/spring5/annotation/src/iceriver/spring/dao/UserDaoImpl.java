package iceriver.spring.dao;

import org.springframework.stereotype.Repository;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/7/15 15:58
 */
@Repository(value = "userDaoImpl1")
public class UserDaoImpl implements UserDao{
    @Override
    public void add() {
        System.out.println("userdaoimpl");
    }
}
