package iceriver.spring.service;

import iceriver.spring.dao.UserDao;
import iceriver.spring.dao.UserDaoImpl;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/7/15 8:16
 */
public class UserService {
    private UserDao userDao = new UserDaoImpl();

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void add(){
        System.out.println("userservice");
        userDao.update();
    }
}
