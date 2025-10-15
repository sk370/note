/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月23日下午12:10:54
 */
package exercises.employee2;

public class Employee {
	private String name;
	private double baseSalary;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getBaseSalary() {
		return baseSalary;
	}
	public void setBaseSalary(double baseSalary) {
		this.baseSalary = baseSalary;
	}
	public Employee() {
		super();
	}
	public Employee(String name, double baseSalary) {
		super();
		this.name = name;
		this.baseSalary = baseSalary;
	}
	public void printSalary() {
	};
}
