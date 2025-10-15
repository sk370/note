package teammanage.domain;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/5/27 10:00
 */
public class Employee {
    private int id;
    private String name;
    private int age;
    private double salary;

    public int getId() {
        return id;
    }

    public Employee(int id, String name, int age, double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    public String getDesc(){
        return id + "\t" + name + "\t" + age + "\t" + salary;
    }

    @Override
    public String toString() {
        return getDesc();
    }
}
