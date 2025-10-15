package fruit.dao.impl;

import fruit.dao.FruitDAO;
import fruit.pojo.Fruit;
import myssm.basedao.BaseDAO;

import java.util.List;

public class FruitDAOImpl extends BaseDAO<Fruit> implements FruitDAO {
    @Override
    public List<Fruit> getFruitList() {
        return super.executeQuery("select * from t_fruit");
    }

    @Override
    public boolean addFruit(Fruit fruit) {
        String sql = "insert into t_fruit values(0,?,?,?,?)";
        int count = super.executeUpdate(sql, fruit.getFname(), fruit.getPrice(), fruit.getFcount(), fruit.getRemark());
        //insert语句返回的是自增列的值，而不是影响行数
        //System.out.println(count);
        return count > 0;
    }

    @Override
    public boolean updateFruit(Fruit fruit) {
        String sql = "update t_fruit set fname = ?, price = ?, fcount = ?, remark = ? where fid = ? ";
        return super.executeUpdate(sql,fruit.getFname(),fruit.getPrice(), fruit.getFcount(), fruit.getRemark(), fruit.getFid()) > 0;
    }

    @Override
    public boolean delFruitByName(String fname) {
        String sql = "delete from t_fruit where fname like ? ";
        return super.executeUpdate(sql, fname) > 0;
    }
    @Override
    public boolean delFruitById(Integer fid) {
        String sql = "delete from t_fruit where fid =  ? ";
        return super.executeUpdate(sql, fid) > 0;
    }

    @Override
    public Fruit getFruitByFname(String fname) {
        return super.load("select * from t_fruit where fname like ? ", fname);
    }
    @Override
    public Fruit getFruitById(Integer fid) {
        return super.load("select * from t_fruit where fid = ? ", fid);
    }
}