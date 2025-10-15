/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月19日下午3:58:20
 */
package com.infinity.characteristic.person;

public class PersonTest {
	public static void main(String[] args) {
		Person p1 = new Faculty();
		Person p2 = new Staff();
//		方式一：向上转型
//		Faculty p3 = (Faculty)p1;
//		p3.degree = "本科";
//		Staff p4 = (Staff)p2;
//		p4.duty = "职员";
//		System.out.println(p3.degree + " " + p4.duty);
//		方式二：重写方法，
		p1.changeDegree("本科");
		p2.changeDuty("职员");
	}
}
