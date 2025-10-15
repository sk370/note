/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月14日下午2:56:43
 */
package com.infinity.keywords;

public class Boy {
	private String name;
	private int age;
	public void setAge(int age) {
		this.age = age;
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
	public Boy() {
		this.setAge(22);
		this.setName("TOM");
	}
	public void marry(Girl girl) {
		System.out.println("我要娶" + girl.getName() + "了！");
	}
}
