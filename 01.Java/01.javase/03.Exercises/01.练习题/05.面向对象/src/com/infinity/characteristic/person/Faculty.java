/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月19日下午3:56:22
 */
package com.infinity.characteristic.person;

public class Faculty extends Employee{
	String degree;
	String level;
	public String changeDegree(String s) {
		degree = s;
		System.out.println(this.degree);
		return degree;
	}
}
