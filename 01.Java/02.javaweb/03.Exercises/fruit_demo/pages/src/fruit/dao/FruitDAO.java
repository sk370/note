package fruit.dao;

import fruit.pojo.Fruit;

import java.util.List;

public interface FruitDAO {
    // 获取指定页面上的库存列表信息，每页显示五条
    List<Fruit> getFruitListByPage(Integer pageNo);

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

    //查询库存总记录条数
    int getFruitCount();
}
