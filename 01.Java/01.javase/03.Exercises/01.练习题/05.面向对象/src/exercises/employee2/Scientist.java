/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月23日下午12:12:50
 */
package exercises.employee2;

public class Scientist extends Employee{
	private double bonus;
	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}
	public Scientist() {
		super();
	}
	public Scientist(String name, double baseSalary, double bonus) {
		super(name, baseSalary);
		this.bonus = bonus;
	}

	@Override
	public void printSalary() {
		System.out.println(this.getName() + "的全年工资为：" + (this.getBaseSalary() + this.bonus));
	}

}
