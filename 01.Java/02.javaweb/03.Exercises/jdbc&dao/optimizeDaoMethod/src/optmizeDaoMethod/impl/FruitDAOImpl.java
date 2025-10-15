package optmizeDaoMethod.impl;

import optmizeDaoMethod.dao.BaseDao;
import optmizeDaoMethod.dao.FruitDAO;
import optmizeDaoMethod.pojo.Fruit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FruitDAOImpl extends BaseDao<Fruit> implements FruitDAO {

    @Override
    public List<Fruit> getFruitList() {
        String sql = "select * from t_fruit";
        return executeQuery(sql);
    }

    /**
     * 添加操作
     * @param fruit
     * @return excuteUpdate返回大于0的整数表示成功
     */
    @Override
    public boolean addFruit(Fruit fruit) {
        String sql = "insert into t_fruit values(0,?,?,?,?)";
        int i = excuteUpdate(sql, fruit.getFname(), fruit.getPrice(), fruit.getFcount(), fruit.getRemark());
        return i > 0;
    }

    /**
     * 更新操作
     * @param fruit
     * @return excuteUpdate返回大于0的整数表示成功
     */
    @Override
    public boolean updateFruit(Fruit fruit) {
        String sql = "update t_fruit set fcount = ? where fid = ? " ;
        return excuteUpdate(sql,fruit.getFcount(), fruit.getFid()) > 0;
    }

    @Override
    public Fruit getFruitByFname(String fname) {
        String sql = "select * from t_fruit where fname like ? ";
        return load(sql, fname);
    }

    /**
     * 删除操作
     * @param fname
     * @return excuteUpdate返回大于0的整数表示成功
     */
    @Override
    public boolean delFruit(String fname) {
        String sql = "delete from t_fruit where fname like ? " ;
        return excuteUpdate(sql, fname) > 0;
    }
}