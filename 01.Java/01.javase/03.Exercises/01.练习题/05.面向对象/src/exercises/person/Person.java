/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月23日下午3:41:30
 */
package exercises.person;

public class Person {
	private String name;
	private char sex;
	private int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public char getSex() {
		return sex;
	}
	public void setSex(char sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Person() {
		super();
	}
	public Person(String name, char sex, int age) {
		super();
		this.name = name;
		this.sex = sex;
		this.age = age;
	}
	public String play() {
		return this.name + "爱玩";
	}
	public void printInfo() {
		System.out.println("的信息：\n姓名：" + this.name + "\n年龄：" + this.age + "\n性别：" + this.sex); 
		this.play();
	}
}
