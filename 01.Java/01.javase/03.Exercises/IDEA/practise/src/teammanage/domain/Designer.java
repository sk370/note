package teammanage.domain;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/5/27 10:01
 */
public class Designer extends Programmer{
    private double bouns;

    public double getBouns() {
        return bouns;
    }

    public Designer(int id, String name, int age, double salary, Equipment equipment, double bouns) {
        super(id, name, age, salary, equipment);
        this.bouns = bouns;
    }

    @Override
    public String toString() {
        return getDesc() + "\t设计师\t" + this.getStatus() + "\t" + bouns + "\t\t" + getEquipment().getDescription();
    }
    public String teamDesc() {
        return getMemberedId() + "/" + getId() + "\t" + getName() + "\t" + getAge() + "\t" + getSalary() + "\t" + bouns;
    }
}
