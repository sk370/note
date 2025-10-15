package top.sk370.shiro.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.sk370.shiro.entity.User;
import top.sk370.shiro.mapper.UserMapper;
import top.sk370.shiro.service.UserService;

import java.util.List;

/**
 * @author zhuyuqi
 * @version v0.0.1
 * @className UserServiceImpl
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/10/01 19:08
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     *
     * @param name
     * @return
     */
    @Override
    public User getUserInfoByName(String name) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",name);
        User user = userMapper.selectOne(queryWrapper);
        return user;
    }

    @Override
    public List<String> getUserRoleInfo(String principal) {
        return userMapper.getUserRoleInfoMapper(principal);
    }
    @Override
    public List<String> getUserPermissionInfo(List<String> roles) {
        return userMapper.getUserPermissionInfoMapper(roles);
    }
}
