package collection.set;

import java.util.HashSet;

/**
 * @author 韩顺平
 * @version 1.0
 */
@SuppressWarnings({"all"})
public class HashSet01 {
    public static void main(String[] args) {
        HashSet set = new HashSet();

        set.add("lucy");//添加成功
        set.add("lucy");//加入不了
        set.add(new Dog("tom"));//OK
        set.add(new Dog("tom"));//Ok
        System.out.println("collection.set=" + set);

    }
}
class Dog { //定义了Dog类
    private String name;

    public Dog(String name) {
        this.name = name;
    }

//    @Override
//    public String toString() {
//        return "Dog{" +
//                "name='" + name + '\'' +
//                '}';
//    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Dog dog = (Dog) o;
//
//        return name.equals(dog.name);
//    }

//    @Override
//    public int hashCode() {
//        return name.hashCode();
//    }
}
