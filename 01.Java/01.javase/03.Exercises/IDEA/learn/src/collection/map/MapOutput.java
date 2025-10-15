package collection.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/7 8:10
 */
public class MapOutput {
    public static void main(String[] args) {
        Employee employee1 = new Employee("张三", 20000.00, 38);
        Employee employee2 = new Employee("lucy", 15000.00, 24);
        Employee employee3 = new Employee("路人甲", 22000.00, 28);
        HashMap hashMap = new HashMap();
        hashMap.put(employee1.getId(), employee1);
        hashMap.put(employee2.getId(), employee2);
        hashMap.put(employee3.getId(), employee3);
        System.out.println(hashMap);
        System.out.println("keySet方法输出：1");
        Set set = hashMap.keySet();
        for( Object key : set){
            Employee emp = (Employee) hashMap.get(key);
            if(emp.getSalary() >= 18000){
                System.out.println(key + " - " + hashMap.get(key));
            }
        }
        System.out.println("keySet方法输出：2");
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Object key =  iterator.next();
            Employee emp = (Employee) hashMap.get(key);
            if(emp.getSalary() >= 18000){
                System.out.println(key + " - " + hashMap.get(key));
            }
        }
        System.out.println("EntrySet输出方法：1");
        Set set1 = hashMap.entrySet();
        for (Object obj : set1){
            Map.Entry m = (Map.Entry)obj;
            Employee e = (Employee) m.getValue();
            if(e.getSalary() >= 18000){
                System.out.println(e);
            }
        }
        System.out.println("EntrySet输出方法：2");
        Iterator iterator1 = set1.iterator();
        while (iterator1.hasNext()) {
            Object next =  iterator1.next();
            Map.Entry m = (Map.Entry)next;
            Employee e = (Employee)m.getValue();
            if(e.getSalary() >= 18000){
                System.out.println(e);
            }
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