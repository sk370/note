/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月11日上午11:42:51
 */
package com.infinity.erxrcise;

public class ClassInstantiation {
	public static void main(String[] args) {
		TestPerson p1 = new TestPerson();
		p1.age = 18;
		p1.name = "小王";
		p1.sex = '男';
		p1.study();
		p1.showAge();
		System.out.println(p1.addAge());
		TestPerson p2 = new TestPerson();
		p2.showAge();		
	}
}
class TestPerson{
	int age;
	String name;
	char sex;
	public void study() {
		System.out.println("Struding");
	}
	public void showAge() {
		System.out.println(age);
	}
	public int addAge() {
		return age+=2;
	}
}