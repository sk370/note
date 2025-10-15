/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月14日下午12:20:35
 */
package com.infinity.overload;

public class Employee {
	String name;
	char sex;
	int age;
	double salary;
	String position;
	public Employee(String name, char sex, int age, double salary, String position) {
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.salary = salary;
		this.position = position;
	}
	public Employee(String name, char sex, int age) {
		this.name = name;
		this.sex = sex;
		this.age = age;
	}
	public Employee(double salary, String position) {
		this.salary = salary;
		this.position = position;
	}
}