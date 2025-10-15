/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月23日下午3:44:41
 */
package exercises.person;

public class Teacher extends Person {
	private int work_age;
	public int getWork_age() {
		return work_age;
	}
	public void setWork_age(int work_age) {
		this.work_age = work_age;
	}
	public Teacher() {
		super();
	}
	public Teacher(String name, char sex, int age, int work_age) {
		super(name, sex, age);
		this.work_age = work_age;
	}
	@Override
	public String play() {
		return super.play() + "象棋";
	}
	public void study() {
		System.out.println("我承诺，我会认真教学");
	}
	@Override
	public void printInfo() {
		System.out.println("老师的信息：\n姓名：" + this.getName() + "\n年龄：" + this.getAge() + "\n性别：" + this.getSex() + "\n工龄：" + this.work_age); 
		this.study();
		System.out.println(this.play());
	}
}
