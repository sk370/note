package optmizeDaoMethod.dao;

import optmizeDaoMethod.pojo.Fruit;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/7/21 11:42
 */
public abstract class BaseDao<T> {//设置为抽象类或者接口
    protected Connection conn ;
    protected PreparedStatement psmt ;
    protected ResultSet rs ;

    final String DRIVER = "com.mysql.jdbc.Driver" ;
    final String URL = "jdbc:mysql://localhost:13306/fruitdb?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    final String USER = "root";
    final String PWD = "dimitre123" ;

    private Class entityClass;//T的Class类对象，通过该对象的newInstance可以创建实例对象

    /**
     * 加载构造器，并确定泛型类型
     */
    public BaseDao() {
        Type genericSuperclass = getClass().getGenericSuperclass();//获取运行类型的父类的类型（带泛型）//optmizeDaoMethod.dao.BaseDao<optmizeDaoMethod.pojo.Fruit>
//        System.out.println(genericSuperclass);
        ParameterizedType parameterizedType = (ParameterizedType)genericSuperclass;//转换为泛型//optmizeDaoMethod.dao.BaseDao<optmizeDaoMethod.pojo.Fruit>
//        System.out.println(parameterizedType);
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();//获取泛型数组
        Type actualType = actualTypeArguments[0];//本文中获取T
//        System.out.println(actualType);

        try {
            entityClass = Class.forName(actualType.getTypeName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 封装获取连接
     * @return
     */
    public Connection getConn(){
        try {
            //1.加载驱动
            Class.forName(DRIVER);
            //2.通过驱动管理器获取连接对象
            conn = DriverManager.getConnection(URL, USER, PWD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 封装关闭连接
     * @param rs 结果集
     * @param psmt  预处理对象
     * @param conn 连接
     */
    public void close(ResultSet rs, PreparedStatement psmt, Connection conn){
        try {
            if (rs != null) {
                rs.close();
            }
            if(psmt!=null){
                psmt.close();
            }
            if(conn!=null && !conn.isClosed()){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 给sql语句设置参数
     * @param psmt
     * @param params
     */
    private void setParams(PreparedStatement psmt, Object... params) throws SQLException {
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                psmt.setObject(i + 1, params[i]);
            }
        }
    }

    /**
     * 抽取增删改方法
     * 增删改方法均经过：1.获取连接  2.编写sql 3.获取预处理对象   4.设置sql参数   5.执行更新  6.释放资源  这6个步骤，区别在于sql语句不同，设置的sql参数不同，所以可以抽取成方法
     * @param sql
     * @param params
     * @return 返回0表示影响行数0行，其他数为真实影响行数
     */
    protected int excuteUpdate(String sql, Object... params){
        // S : 执行更新，返回受影响行数的值
        boolean insertFlag = false;//S1
        insertFlag = sql.trim().toUpperCase().startsWith("INSERT");//S2

        try {
            conn = getConn();

            if (insertFlag){//S3，当为插入语句时，返回受影响那一行的值
                psmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);//S4
            }else {
                psmt = conn.prepareStatement(sql);
            }
            setParams(psmt, params);
            int count = psmt.executeUpdate();

            if(insertFlag){
                rs = psmt.getGeneratedKeys();//S5
                if(rs.next()){//S6
                    return ((Long)rs.getLong(1)).intValue();//S7---E
                }
            }
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(rs, psmt, conn);
        }
        return 0;
    }

    /**
     * 使用反射，向实例化的T真实类型注入属性值，被查询方法调用
     * @param obj
     * @param property
     * @param propertyValue
     */
    private void setValue(Object obj, String property, Object propertyValue){
        Class clazz = obj.getClass();
        try {
            Field field = clazz.getDeclaredField(property);
            if(field != null){
                field.setAccessible(true);
                field.set(obj, propertyValue);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    /**
     * 抽取查询方法，并实现能够各类商品的查询返回
     * @return
     */
    public List<T> executeQuery(String sql, Object... params) {
        List<T> list = new ArrayList<>();
        try {
            conn = getConn();
            psmt = conn.prepareStatement(sql);
            setParams(psmt, params);
            rs = psmt.executeQuery();
            // 解析rs——使用prepareStatement对象获取元数据的方法getMetaData()
            // 元数据——有多少列，列叫什么名字，每列的值还是在prepareStatement对象中
            ResultSetMetaData metaData = rs.getMetaData();//获取元数据对象
            int columnCount = metaData.getColumnCount();//获取结果集的列数

            while(rs.next()){
                T entity = null;
                try {
                    entity = (T)entityClass.newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }

                for (int i = 0; i < columnCount; i++) {
                    String columnName = metaData.getColumnName(i + 1);//获取每列的名字
                    Object columnValue = rs.getObject(i + 1);//获取每列的值
                    setValue(entity, columnName, columnValue);
                }
                list.add(entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(rs, psmt, conn);
        }
        return list;
    }

    /**
     * 执行查询，返回单个对象
     * @param sql
     * @param params
     * @return
     */
    public T load(String sql, Object... params) {
        try {
            conn = getConn();
            psmt = conn.prepareStatement(sql);
            setParams(psmt, params);
            rs = psmt.executeQuery();
            // 解析rs——使用prepareStatement对象获取元数据的方法getMetaData()
            // 元数据——有多少列，列叫什么名字，每列的值还是在prepareStatement对象中
            ResultSetMetaData metaData = rs.getMetaData();//获取元数据对象
            int columnCount = metaData.getColumnCount();//获取结果集的列数

            if(rs.next()){
                T entity = null;
                try {
                    entity = (T)entityClass.newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }

                for (int i = 0; i < columnCount; i++) {
                    String columnName = metaData.getColumnName(i + 1);//获取每列的名字
                    Object columnValue = rs.getObject(i + 1);//获取每列的值
                    setValue(entity, columnName, columnValue);
                }
                return entity;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(rs, psmt, conn);
        }
        return null;
    }

    /**
     * 执行复杂查询，返回例如统计结果
     * @param sql
     * @param params
     * @return
     */
    public Object[] excuteConplexQuery(String sql, Object... params) {
        try {
            conn = getConn();
            psmt = conn.prepareStatement(sql);
            setParams(psmt, params);
            rs = psmt.executeQuery();
            // 解析rs——使用prepareStatement对象获取元数据的方法getMetaData()
            // 元数据——有多少列，列叫什么名字，每列的值还是在prepareStatement对象中
            ResultSetMetaData metaData = rs.getMetaData();//获取元数据对象
            int columnCount = metaData.getColumnCount();//获取结果集的列数
            Object[] colnumValueArr = new Object[columnCount];

            if(rs.next()){
                for (int i = 0; i < columnCount; i++) {
                    Object columnValue = rs.getObject(i + 1);//获取每列的值
                    colnumValueArr[i] = columnValue;
                }
                return colnumValueArr;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(rs, psmt, conn);
        }
        return null;
    }
}
