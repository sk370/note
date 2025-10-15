/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月14日下午2:28:14
 */
package com.infinity.characteristic;
// 封装
public class PersonTest {
	public static void main(String[] args) {
		Person p = new Person();
		System.out.println(p.getAge() + "," + p.getName());
		p.setAge(12);
		System.out.println(p.getAge() + "," + p.getName());
		Person p1 = new Person(16, "Davida");
		System.out.println(p1.getAge() + "," + p1.getName());
	}
}
