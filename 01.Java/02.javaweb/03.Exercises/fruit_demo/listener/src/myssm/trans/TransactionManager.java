package myssm.trans;

import myssm.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/7/23 17:50
 */
public class TransactionManager {

    /**
     * 开启事务
     * @throws SQLException
     */
    public static void beginTrans() throws SQLException {
        ConnectionUtil.getConn().setAutoCommit(false);
    }

    /**
     * 提交事务
     * @throws SQLException
     */
    public static void commit() throws SQLException {
        Connection conn = ConnectionUtil.getConn();
        conn.commit();
        ConnectionUtil.closeConn();//关闭连接，释放threadlocal
    }

    /**
     * 回滚事务
     * @throws SQLException
     */
    public static void rollback() throws SQLException {
        Connection conn = ConnectionUtil.getConn();
        conn.rollback();
        ConnectionUtil.closeConn();//关闭连接，释放threadlocal
    }
}
