/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月19日下午3:57:10
 */
package com.infinity.characteristic.person;

public class Staff extends Employee{
	String duty;
	public String changeDuty(String s) {
		duty = s;
		System.out.println(this.duty);
		return duty;
	}
}
