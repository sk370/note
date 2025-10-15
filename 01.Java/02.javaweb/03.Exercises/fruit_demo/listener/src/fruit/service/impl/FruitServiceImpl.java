package fruit.service.impl;

import fruit.dao.FruitDAO;
import fruit.pojo.Fruit;
import fruit.service.FruitService;
import myssm.utils.ConnectionUtil;

import java.util.List;

public class FruitServiceImpl implements FruitService {

    private FruitDAO fruitDAO = null ;

    @Override
    public List<Fruit> getFruitListByPage(String keyword, Integer pageNo) {
        System.out.println(ConnectionUtil.getConn());//打印FruitController中，index方法调用的getFruitListByPage方法的连接，与index方法调用的getFruitCount方法的连接作比较，看是不是同一个连接，是不是经过了过滤器
        return fruitDAO.getFruitListByPage(keyword,pageNo);
    }

    @Override
    public void addFruit(Fruit fruit) {
        fruitDAO.addFruit(fruit);
//        //测试事务：在添加水果的同时，修改了既有水果的信息，看是不是会回滚
//        Fruit fruit1 = fruitDAO.getFruitById(2);
//        fruit1.setFcount(20);
//        fruitDAO.updateFruit(fruit);
//        //测试事务结束
    }

    @Override
    public Fruit getFruitById(Integer fid) {
        return fruitDAO.getFruitById(fid);
    }

    @Override
    public void delFruitById(Integer fid) {
        fruitDAO.delFruitById(fid);
    }

    @Override
    public Integer getFruitCount(String keyword) {
        int count = fruitDAO.getFruitCount(keyword);
//        int pageCount = (count+5-1)/5 ;
        System.out.println(ConnectionUtil.getConn());//打印FruitController中，index方法调用的getFruitCount方法的连接，与index方法调用的getFruitListByPage方法的连接作比较，看是不是同一个连接，是不是经过了过滤器
//        return pageCount;
        return count;
    }


    @Override
    public void updateFruit(Fruit fruit) {
        fruitDAO.updateFruit(fruit);
    }
}
