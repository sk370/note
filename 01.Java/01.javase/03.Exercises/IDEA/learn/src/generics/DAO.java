package generics;

import org.junit.Test;

import java.util.*;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/8 21:05
 */
public class DAO <T>{
    private Map<String, T> map= new HashMap<>() ;
    public void save(String id, T entity){
        map.put(id, entity);
    }
    public T get(String id){
        return map.get(id);
    }
    public void update(String id, T entity){
        map.put(id, entity);
    }
    public List<T> list(){
        List<T> arrayList = new ArrayList<>();
        Set<String> strings = map.keySet();
        for (String string : strings){
            arrayList.add(get(string));
        }
        return arrayList;
    }
    public void delete(String id){
        map.remove(id);
    }
    @Test
    public void test(){
        DAO<User> dao = new DAO<>();
        dao.save("001", new User(2, 18, "jack"));
        dao.save("002", new User(3, 19, "smith"));
        dao.save("003", new User(4, 20, "david"));
        List<User> list = dao.list();
        System.out.println(list);



    }
}
class User{
    private int id;
    private int age;
    private String name;

    public User(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}