/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月14日下午3:01:25
 */
package com.infinity.keywords;

public class Girl {
	private String name;
	private int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void marry(Boy boy) {
		System.out.println("我要嫁给" + boy.getName() + "了！");
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Girl() {
		this.setName("race");
		this.setAge(18);
	}
	public void compare(Girl girl) {
		if(this.age <= girl.age) {
			System.out.println("我比" + girl.getName() + "年龄小！");
		}else {
			System.out.println("我比" + girl.getName() + "年龄大！");
		}
	}
}
