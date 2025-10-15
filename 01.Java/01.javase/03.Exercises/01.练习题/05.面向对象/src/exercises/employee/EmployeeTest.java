/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月23日上午11:47:32
 */
package exercises.employee;

public class EmployeeTest {
	public static void main(String[] args) {
		Employee manager = new Manager("张三", 300, 30);
		Employee worker = new Worker("李四", 300, 30);
		manager.printSalary();
		worker.printSalary();
	}
}
