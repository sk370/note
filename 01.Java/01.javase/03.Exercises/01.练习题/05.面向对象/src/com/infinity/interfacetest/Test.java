/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月26日下午4:58:38
 */
package com.infinity.interfacetest;

interface A {
	int x = 0;
}

class B {
	int x = 1;
}

public class Test extends B implements A {
	int x = 3;

	public void pX() {
		System.out.println(x); // super.x A.x
	}

	public static void main(String[] args) {
		new Test().pX();
	}
}
