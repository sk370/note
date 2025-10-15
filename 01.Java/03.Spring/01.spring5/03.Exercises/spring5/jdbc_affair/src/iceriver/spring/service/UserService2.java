package iceriver.spring.service;

import iceriver.spring.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/7/16 16:29
 */

@Service
public class UserService2 {
    @Autowired
    private UserDao userDao;

    public void accoutMoney(){
        userDao.reduceMoney();
//        int i = 110/0 ;
        userDao.addMoney();
    }

}
