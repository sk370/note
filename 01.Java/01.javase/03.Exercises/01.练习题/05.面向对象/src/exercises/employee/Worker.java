/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月23日上午11:43:29
 */
package exercises.employee;

public class Worker extends Employee {
	private double level = 1.0;
	public double getLevel() {
		return level;
	}
	public void setLevel(double level) {
		this.level = level;
	}
	public Worker() {
		super();
	}
	public Worker(String name, double wage, int days) {
		super(name, wage, days);
	}
	public void printSalary() {
		System.out.println(this.getName() + "的工资为" + (this.getWage() * this.getDays() * this.level));
	}
}
