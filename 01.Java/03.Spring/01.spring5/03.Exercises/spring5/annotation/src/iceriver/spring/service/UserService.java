package iceriver.spring.service;

import iceriver.spring.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/7/15 15:16
 */
//@Component(value="userService")//<bean id="userService" class=""/>
@Controller//不配置value时，默认为userService——类名的首字母小写
public class UserService {
//    @Autowired
//    @Qualifier(value="userDaoImpl1")
    @Resource(name = "userDaoImpl1")
    private UserDao userDao;
    @Value(value = "dadada")
    private String name;
    public void add(){
        userDao.add();
        System.out.println("userservice" + name);
    }
}
