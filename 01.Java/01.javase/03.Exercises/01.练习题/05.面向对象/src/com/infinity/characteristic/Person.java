/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月14日下午2:25:34
 */
package com.infinity.characteristic;

public class Person {
	private int age;
	private String name;
	public void setAge(int age) {
		if(age > 0 && age < 130) {
			this.age = age;
		}else {
			System.out.println("年龄有误！");
		}
	}
	public int getAge() {
		return this.age;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	public Person() {
		this.setAge(18);
	}
	public Person(int age, String name) {
		this.setAge(age);
		this.setName(name);
	}
}
