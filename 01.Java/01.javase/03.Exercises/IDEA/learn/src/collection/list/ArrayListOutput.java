package collection.list;

import java.util.*;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/8 11:47
 */
public class ArrayListOutput {
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add(new Dog("旺财", 18));
        list.add(new Dog("哮天犬", 19800));
        list.add(new Dog("八公", 4));
        System.out.println("增强for循环");
        for(Object list1 : list){
            System.out.println(list1);
        }
        System.out.println("迭代器");
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            Object next =  iterator.next();
            System.out.println(next);
        }
    }
}
class Dog{
    private String name;
    private int age;
    public Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}