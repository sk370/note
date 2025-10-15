/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月14日下午2:39:38
 */
package com.infinity.overload;

public class Student {
	private String name;
	private int age;
	private String school;
	private String major;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public Student(String name, int age) {
		this.setName(name);
		this.setAge(age);
	}
	public Student(String name, int age, String school) {
		this(name, age);
		this.setSchool(school);
	}
	public Student(String name, int age, String school, String major) {
		this(name, age, school);
		this.setMajor(major);
	}
	
}
