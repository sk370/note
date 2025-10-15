package iceriver.mybatis.mapper;

import iceriver.mybatis.pojo.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/7/27 11:46
 */
public interface DynamicSQLMapper {
    /**
     * 多条件查询
     * @param emp
     * @return
     */
    List<Emp> getEmpByCondition(Emp emp);

    /**
     * 测试choose、when、otherwise
     * @param emp
     * @return
     */
    List<Emp> getEmpByChoose(Emp emp);

    /**
     * foreach-数组，实现批量删除
     * @param eids
     * @return
     */
    int deleteMoreByArray(@Param("eids") Integer[] eids);

    /**
     * foreach-List集合，实现批量添加
     * 通过list集合实现批量添加
     */
    int insertMoreByList(@Param("emps") List<Emp> emps);
}
