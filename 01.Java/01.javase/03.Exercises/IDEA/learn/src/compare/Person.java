package compare;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/5 9:19
 */
public class Person implements Comparable{
    public String name;
    public int age;
    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }
    @Override
    public int compareTo(Object o) {
        if(o != null && o instanceof Person){
            Person p = (Person) o;
            return Integer.compare(this.age, p.age);
        }
        throw new RuntimeException("传入类型不一致");
    }
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static void main(String[] args) {
        Person[] p = new Person[4];
        p[0] = new Person("zhangs", 18);
        p[1]= new Person("lisss", 25);
        p[2] = new Person("jack", 14);
        p[3] = new Person("lucy", 42);
        System.out.println(Arrays.toString(p));
        Arrays.sort(p);
        System.out.println(Arrays.toString(p));
        Arrays.sort(p, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Person p1 = (Person) o1;
                Person p2 = (Person) o2;
                return -p1.name.compareTo(p2.name);
            }
        });
        System.out.println(Arrays.toString(p));
    }
}
