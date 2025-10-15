package top.sk370.shiro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import top.sk370.shiro.entity.User;

import java.util.List;

/**
 * @author zhuyuqi
 * @version v2.0
 * @interfaceName UserMapper
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/10/01 19:07
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
    /**
     * 自定义mapper方法：角色检查
     * @param principal
     * @return
     */
    @Select("SELECT NAME FROM role WHERE id IN (SELECT rid FROM role_user WHERE uid=(SELECT id FROM USER WHERE NAME=#{principal}))")
    List<String> getUserRoleInfoMapper(@Param("principal")String principal );

    /**
     * 自定义mapper方法：权限检查
     * @param roles
     * @return
     */
    @Select({
            "<script>",
            "select info FROM permissions WHERE id IN ",
            "(SELECT pid FROM role_ps WHERE rid IN (",
            "SELECT id FROM role WHERE NAME IN ",
            "<foreach collection='roles' item='name' open='(' separator=',' close=')'>",
            "#{name}",
            "</foreach>",
            "))",
            "</script>"
    })
    List<String> getUserPermissionInfoMapper(@Param("roles")List<String> roles);
}
