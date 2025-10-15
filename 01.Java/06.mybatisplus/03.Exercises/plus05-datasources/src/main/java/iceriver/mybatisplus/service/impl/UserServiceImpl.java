package iceriver.mybatisplus.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import iceriver.mybatisplus.bean.User;
import iceriver.mybatisplus.mapper.UserMapper;
import iceriver.mybatisplus.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/8/5 18:37
 */
@Service
@DS("master")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
