/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月11日上午10:18:44
 */
package com.infinity.erxrcise;

public class CopyPerson {
	public static void main(String[] args) {
		Person p2 = new Person();
		System.out.println(p2.age);
		System.out.println(p2.name);
		System.out.println(p2.sum(1,2));
		NewPerson p3 = new NewPerson();
		Person p4 = p3.copyPerson(p2);
		System.out.println(p4.name);
		System.out.println(p4.age);
		System.out.println(p4.sum(3,4));
	}
}
class Person {
	int age = 18;
	String name = "小赵";
	public int sum(int a, int b) {
		return a + b;
	}
}
class NewPerson{
	public Person copyPerson(Person p) {
		Person p1 = new Person();
//		p1 = p;
		p1.age = p.age;
		p1.age = 24;
		p1.name = p.name;
		p1.name = "小小赵";
		return p1;
	}
}