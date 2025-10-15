package myssm.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/7/23 18:03
 */
public class ConnectionUtil {
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:13306/fruitdb?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    public static final String USER = "root";
    public static final String PWD = "dimitre123";

    private static Connection createConn(){
        try {
            //1.加载驱动
            Class.forName(DRIVER);
            //2.通过驱动管理器获取连接对象
            return DriverManager.getConnection(URL, USER, PWD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Connection getConn(){
        Connection connection = threadLocal.get();
        if(connection == null){
            connection = createConn();
            threadLocal.set(connection);
        }
        return threadLocal.get();
    }

    /**
     * 关闭连接，并清空threadlocal的连接
     * @throws SQLException
     */
    public static void closeConn() throws SQLException {
        Connection connection = threadLocal.get();
        if(connection == null){
            return;
        }
        if(!connection.isClosed()){
            connection.close();
            threadLocal.set(null);
        }
    }
}
