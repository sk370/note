/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月23日下午3:48:56
 */
package exercises.person;

public class PersonTest {
	public static void main(String[] args) {
		Person[] person = new Person[4];
		person[0] = new Student("张三", '男', 18, 1200310);
		person[1] = new Student("李四", '女', 19, 1200310);
		person[2] = new Teacher("王五", '男', 20, 12);
		person[3] = new Teacher("周六", '女', 21, 11);
		for(int i = 0; i < person.length; i++) {
			person[i].printInfo();
		}
	}
}
