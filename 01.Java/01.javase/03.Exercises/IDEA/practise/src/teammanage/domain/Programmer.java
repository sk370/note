package teammanage.domain;

import teammanage.service.Status;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/5/27 10:01
 */
public class Programmer extends Employee{
    private int memberedId;
    private Status status = Status.FREE;

    public Status getStatus() {
        return status;
    }

    public void setMemberedId(int memberedId) {
        this.memberedId = memberedId;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    private Equipment equipment;

    public int getMemberedId() {
        return memberedId;
    }

    public Programmer(int id, String name, int age, double salary, Equipment equipment) {
        super(id, name, age, salary);
        this.equipment = equipment;
    }

    @Override
    public String toString() {
        return getDesc() + "\t程序员\t" + status + "\t\t\t\t" + equipment.getDescription();
    }

    public String teamDesc() {
        return memberedId + "/" + getId() + "\t" + getName() + "\t" + getAge() + "\t" + getSalary();
    }
}
