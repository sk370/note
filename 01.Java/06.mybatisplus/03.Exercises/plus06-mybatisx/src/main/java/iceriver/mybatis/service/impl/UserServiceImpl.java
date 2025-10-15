package iceriver.mybatis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import iceriver.mybatis.bean.User;
import iceriver.mybatis.service.UserService;
import iceriver.mybatis.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author iceri
* @description 针对表【user】的数据库操作Service实现
* @createDate 2022-08-05 19:28:52
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




