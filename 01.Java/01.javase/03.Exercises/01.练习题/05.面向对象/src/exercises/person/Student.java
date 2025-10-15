/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月23日下午3:44:41
 */
package exercises.person;

public class Student extends Person {
	private int sut_id;
	public int getSut_id() {
		return sut_id;
	}
	public void setSut_id(int sut_id) {
		this.sut_id = sut_id;
	}
	public Student() {
		super();
	}
	public Student(String name, char sex, int age, int sut_id) {
		super(name, sex, age);
		this.sut_id = sut_id;
	}
	@Override
	public String play() {
		return super.play() + "足球";
	}
	public void study() {
		System.out.println("我承诺，我会好好学习");
	}
	@Override
	public void printInfo() {
		System.out.println("学生的信息：\n姓名：" + this.getName() + "\n年龄：" + this.getAge() + "\n性别：" + this.getSex() + "\n学号：" + this.sut_id); 
		this.study();
		System.out.println(this.play());
	}
}
