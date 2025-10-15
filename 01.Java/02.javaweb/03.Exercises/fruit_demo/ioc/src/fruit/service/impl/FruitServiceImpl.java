package fruit.service.impl;

import fruit.dao.FruitDAO;
import fruit.pojo.Fruit;
import fruit.service.FruitService;

import java.util.List;

public class FruitServiceImpl implements FruitService {

    private FruitDAO fruitDAO = null ;

    @Override
    public List<Fruit> getFruitListByPage(String keyword, Integer pageNo) {
        return fruitDAO.getFruitListByPage(keyword,pageNo);
    }

    @Override
    public void addFruit(Fruit fruit) {
        fruitDAO.addFruit(fruit);
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
//        return pageCount;
        return count;
    }

    @Override
    public void updateFruit(Fruit fruit) {
        fruitDAO.updateFruit(fruit);
    }
}
