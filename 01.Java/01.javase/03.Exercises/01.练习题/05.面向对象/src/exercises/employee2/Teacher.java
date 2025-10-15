/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月23日下午12:12:50
 */
package exercises.employee2;

public class Teacher extends Employee{
	private double wage;
	private int num;
	public double getWage() {
		return wage;
	}
	public void setWage(double wage) {
		this.wage = wage;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public Teacher() {
		super();
	}
	public Teacher(String name, double baseSalary, double wage, int num) {
		super(name, baseSalary);
		this.wage = wage;
		this.num = num;
	}
	@Override
	public void printSalary() {
		System.out.println(this.getName() + "的全年工资为：" + (this.getBaseSalary() + this.wage * this.num));
	}

}
