/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月23日下午12:12:50
 */
package exercises.employee2;

public class Wroker extends Employee{
	public Wroker() {
		super();
	}
	public Wroker(String name, double baseSalary) {
		super(name, baseSalary);
	}
	@Override
	public void printSalary() {
		System.out.println(this.getName() + "的全年工资为：" + this.getBaseSalary());
	}

}
