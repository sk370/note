package dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/7/3 15:43
 */
public abstract class BaseDao {
    private QueryRunner queryRunner = new QueryRunner();

    /**
     * 执行DML语句，执行成功返回受影响的行数，失败返回-1
     * @param sql
     * @param args
     * @return
     */
    public int update(String sql, Object ...args){
        Connection connection = JDBCUtils.getConnection();
        try {
            return queryRunner.update(connection, sql, args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
//        return -1;
    }

    /**
     * 查询返回一个JavaBean的sql语句，未查到返回null
     * @param type  返回的对象类型
     * @param sql
     * @param args
     * @param <T>   返回类型的泛型
     * @return
     */
    public <T> T queryForOne(Class<T> type, String sql, Object ...args){
        Connection connection = JDBCUtils.getConnection();
        try {
            return queryRunner.query(connection, sql, new BeanHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
//        return null;
    }

    /**
     * 查询返回多个JavaBean的sql语句，未查到返回null
     * @param type
     * @param sql
     * @param args
     * @param <T>
     * @return
     */
    public <T> List<T> queryForList(Class<T> type, String sql, Object ...args){
        Connection connection = JDBCUtils.getConnection();
        try {
            return queryRunner.query(connection, sql, new BeanListHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
//        return null;
    }

    /**
     * 查询返回一行一列的sql语句，未查到返回null
     * @param sql
     * @param args
     * @return
     */
    public Object queryForSingleValue(String sql, Object ...args){
        Connection connection = JDBCUtils.getConnection();
        try {
            return queryRunner.query(connection, sql, new ScalarHandler<>(), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
//        return null;
    }
}
