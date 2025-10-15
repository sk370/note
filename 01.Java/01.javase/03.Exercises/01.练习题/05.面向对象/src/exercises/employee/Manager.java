/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月23日上午11:43:29
 */
package exercises.employee;

public class Manager extends Employee {
	private double baseSalary = 1000.0;
	private double level = 1.0;
	public double getLevel() {
		return level;
	}
	public void setLevel(double level) {
		this.level = level;
	}
	public Manager() {
		super();
	}
	public double getBaseSalary() {
		return baseSalary;
	}
	public void setBaseSalary(double baseSalary) {
		this.baseSalary = baseSalary;
	}
	public Manager(String name, double wage, int days) {
		super(name, wage, days);
	}
	public void printSalary() {
		System.out.println(this.getName() + "的工资为" + (this.baseSalary + this.getWage() * this.getDays() * this.level));
	}
}
