package iceriver.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import iceriver.mybatisplus.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/8/4 21:01
 */
public interface UserMapper extends BaseMapper<User> {
    /**
     * 自定义crud方法：根据id查询用户信息为map集合
     * @param id
     * @return
     */
    Map<String, Object> selectMapById(Long id);

    /**
     * 自定义分页查询方法：根据年龄查询用户信息并分页
     * @param page mybatis-plus提供的分页对象，必须位于第一个参数的位置
     * @param age
     * @return
     */
    Page<User> selectPageVo(@Param("page") Page<User> page,
                            @Param("age") Integer age);

}
