package teammanage.domain;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/5/27 10:01
 */
public class Architect extends Designer{
    private int stock;

    public Architect(int id, String name, int age, double salary, Equipment equipment, double bouns, int stock) {
        super(id, name, age, salary, equipment, bouns);
        this.stock = stock;
    }
    @Override
    public String toString() {
        return getDesc() + "\t架构师\t" + this.getStatus() + "\t" + getBouns() + "\t" + stock + "\t" + getEquipment().getDescription();
    }
    public String teamDesc() {
        return getMemberedId() + "/" + getId() + "\t" + getName() + "\t" + getAge() + "\t" + getSalary() + "\t" + getBouns() + "\t" + stock;
    }
}
