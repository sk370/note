/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月19日下午4:38:51
 */
package com.infinity.equals;

import java.util.Date;

public class Person {
	String id;
	char sex;
	String name;
	String residence;
	Date birthday;
	public Person(String id, char sex, String name, String residence, Date birthday) {
		super();
		this.id = id;
		this.sex = sex;
		this.name = name;
		this.residence = residence;
		this.birthday = birthday;
	}
	@Override
	public String toString() {
		return id + name + residence;
	}
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj instanceof Person) {
			Person p = (Person)obj;
			if(this.id.equals(p.id)) {
				return true;
			}
		}
		return false;
	}
	public static void main(String[] args) {
		Person p1 = new Person("123131415152", '男', "张三","甘肃天水", new Date(14,3,1994));
		Person p2 = new Person("123131415152", '女', "李四","山西大同", new Date(14,3,1994));
		Person p3 = new Person("12315451", '男', "张三","陕西西安", new Date(14,3,1994));
		System.out.println(p1.toString());
		System.out.println(p1.equals(p2));
		System.out.println(p1.equals(p3));
	}
}
