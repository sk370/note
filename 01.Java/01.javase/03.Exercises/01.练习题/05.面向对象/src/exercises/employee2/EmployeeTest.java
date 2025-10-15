/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月23日下午12:21:19
 */
package exercises.employee2;

public class EmployeeTest {
	public static void main(String[] args) {
		Employee[] employee = new Employee[5];
		employee[0] = new Wroker("工人", 100000);
		employee[1] = new Wroker("农民", 50000);
		employee[2] = new Waiter("服务员", 80000);
		employee[3] = new Teacher("老师", 100000, 100, 100);
		employee[4] = new Scientist("科学家", 100000, 200000);
		for(int i = 0; i < employee.length; i++) {
			employee[i].printSalary();
		}		
	}
}	
