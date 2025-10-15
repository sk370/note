package collection.set;

import java.util.*;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/7 8:53
 */
public class HashSetTest {
    public static void main(String[] args) {
        Employee employee1 = new Employee("张三", 20000.00, 38);
        Employee employee2 = new Employee("lucy", 15000.00, 24);
        Employee employee3 = new Employee("路人甲", 22000.00, 28);
        HashSet hashSet = new HashSet();
        hashSet.add(employee1);
        hashSet.add(employee2);
        hashSet.add(employee3);
        System.out.println(hashSet);
        for( Object obj : hashSet){
                System.out.println(obj.getClass());
        }
        Iterator iterator = hashSet.iterator();
        while (iterator.hasNext()) {
            Object next =  iterator.next();
            System.out.println(next.getClass());
            Employee e = (Employee)next;
            System.out.println(e.getClass());
            System.out.println(e.getId());
        }

    }
}
class Employee{
    private String name;
    private double salary;
    private int id;

    public Employee() {
    }

    public Employee(String name, double salary, int id) {
        this.name = name;
        this.salary = salary;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", id=" + id +
                "}";
    }
}