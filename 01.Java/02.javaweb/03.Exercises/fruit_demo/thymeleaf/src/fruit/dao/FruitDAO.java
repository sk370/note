package fruit.dao;

import fruit.pojo.Fruit;

import java.util.List;

public interface FruitDAO {
    //查询库存列表
    List<Fruit> getFruitList();

    //新增库存
    boolean addFruit(Fruit fruit);

    //修改库存
    boolean updateFruit(Fruit fruit);

    //根据名称查询特定库存
    Fruit getFruitByFname(String fname);

    //根据名称删除特定库存记录
    boolean delFruitByName(String fname);

    //根据id删除特定库存记录
    boolean delFruitById(Integer fid);

    //根据id获取水果
    Fruit getFruitById(Integer fid);
}
