package iceriver.mybatis.mapper;
import org.apache.ibatis.annotations.Param;

import iceriver.mybatis.bean.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author iceri
* @description 针对表【user】的数据库操作Mapper
* @createDate 2022-08-05 19:28:52
* @Entity iceriver.mybatis.bean.User
*/
public interface UserMapper extends BaseMapper<User> {
    int insertSelective(User user);

}




