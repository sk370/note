/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月24日下午7:14:36
 */
package com.infinity.keywords.statictest;

public class TestFrock {
	public static void main(String[] args) {
		Frock[] frocks = new Frock[3];
		for (int i = 0; i < frocks.length; i++) {
			frocks[i] = new Frock();
			System.out.println(frocks[i].getCurrentNum());
		}
	}
}
