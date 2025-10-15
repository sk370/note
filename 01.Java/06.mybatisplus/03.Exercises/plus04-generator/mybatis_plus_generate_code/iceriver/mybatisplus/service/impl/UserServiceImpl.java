package iceriver.mybatisplus.service.impl;

import iceriver.mybatisplus.entity.User;
import iceriver.mybatisplus.mapper.UserMapper;
import iceriver.mybatisplus.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhuyuqi
 * @since 2022-08-05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
