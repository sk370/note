/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月14日下午3:12:34
 */
package com.infinity.keywords;

public class Test {
	public static void main(String[] args) {
		Boy b = new Boy();
		Girl g1 = new Girl();
		Girl g2 = new Girl();
		g2.setName("Jane");
		g2.setAge(18);
		b.marry(g1);
		g1.marry(b);
		g1.compare(g2);
	}
}
