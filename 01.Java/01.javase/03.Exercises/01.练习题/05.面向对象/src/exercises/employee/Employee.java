/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月23日上午11:39:23
 */
package exercises.employee;

public class Employee {
	private String name;
	private double wage;
	private int days;
	public String game = "mssj";
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getWage() {
		return wage;
	}
	public void setWage(double wage) {
		this.wage = wage;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public Employee() {
		super();
	}
	public Employee(String name, double wage, int days) {
		super();
		this.name = name;
		this.wage = wage;
		this.days = days;
	}
	public void printSalary() {
		
	}
}
