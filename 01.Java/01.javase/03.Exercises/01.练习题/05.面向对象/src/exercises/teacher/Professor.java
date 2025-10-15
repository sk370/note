/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月23日上午11:06:53
 */
package exercises.teacher;

public class Professor extends Teacher{
	private double salaryRatio = 1.3;	
	public double getSalaryRatio() {
		return salaryRatio;
	}
	public void setSalaryRatio(double salaryRatio) {
		this.salaryRatio = salaryRatio;
	}
	public Professor() {
		super();
	}
	public Professor(String name, int age, String post, double salary) {
		super(name, age, post, salary);
	}
	@Override
	public String toString() {
		return "Professor " + super.toString() + "salaryRatio" + this.salaryRatio + "]";
	}
	@Override
	public void introduce() {
		System.out.println(toString());
	}
}
