/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月23日上午11:06:53
 */
package exercises.teacher;

public class Lecturer extends Teacher{
	private double salaryRatio = 1.1;	
	public double getSalaryRatio() {
		return salaryRatio;
	}
	public void setSalaryRatio(double salaryRatio) {
		this.salaryRatio = salaryRatio;
	}
	public Lecturer() {
		super();
	}
	public Lecturer(String name, int age, String post, double salary) {
		super(name, age, post, salary);
	}
	@Override
	public String toString() {
		return "Lecturer " + super.toString() + "salaryRatio" + this.salaryRatio + "]";
	}
	@Override
	public void introduce() {
		toString();
	}
}
