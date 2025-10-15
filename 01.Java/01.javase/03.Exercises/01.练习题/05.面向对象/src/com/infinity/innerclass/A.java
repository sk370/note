/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月25日上午9:36:13
 */
package com.infinity.innerclass;

public class A {
	private String name;
	void f1() {
		class B{
			final String NAME = "zyq";
			void show() {
				System.out.println(NAME);
				System.out.println(name);
				System.out.println(A.this.name);
			}
		}
		B b = new B();
		b.show();
	}
	public static void main(String[] args) {
		A a = new A();
		a.f1();
	}
}
