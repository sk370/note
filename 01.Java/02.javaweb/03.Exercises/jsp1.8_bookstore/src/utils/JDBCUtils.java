package utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/7/3 13:47
 */
public class JDBCUtils {
    private static DataSource ds;
    private static ThreadLocal<Connection> conns = new ThreadLocal<>();//线程池，保证连接事务的一致性【下单从始至终是同一个连接】

    /**
     * 初始化ds对象
     */
    static{
        Properties properties = new Properties();
        try {
            //方式一：以IO流形式加载
//            properties.load(new FileInputStream("jsp1.8\\src\\druid.properties"));
            //方式二：使用反射的方式加载
            InputStream inputStream = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            properties.load(inputStream);
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /**
     * 获取数据库连接池的连接
     * @return
     */
    public static Connection getConnection(){
        Connection conn = conns.get();//从线程获取连接
        if(conn == null){
            try {
                conn = ds.getConnection();//从连接池获取连接
                conns.set(conn);//将该连接保存至线程
                conn.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;



//        Connection connection = null;
//        try {
//            connection = ds.getConnection();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return connection;
    }

    /**
     * 闭数据库连接，放回连接池
     * @param conn
     */
//    public static void close(Connection conn){
//        if(conn != null){
//            try {
//                conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    /**
     * 提交事务，并关闭连接
     */
    public static void commitAndClose(){
        Connection connection = conns.get();//从线程获取连接
        if(connection != null){//该连接有使用记录，操作过数据库
            try {
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        conns.remove();//从线程移除连接【因为Tomcat底层使用了线程池】
    }

    public static void rollbackAndClose() {
        Connection connection = conns.get();//从线程获取连接
        if(connection != null){//该连接有使用记录，操作过数据库
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        conns.remove();//从线程移除连接【因为Tomcat底层使用了线程池】
    }
}
