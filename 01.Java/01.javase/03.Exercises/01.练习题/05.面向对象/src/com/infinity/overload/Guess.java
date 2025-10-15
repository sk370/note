/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月14日下午12:09:43
 */
package com.infinity.overload;
public class Guess {
	public static void main(String[] args) {
		Guess gs = new Guess();
		gs.calcuate();
	}
	public double method(double a, double b) {
		return a + b;
	}
	public double method(double a, int b) {
		return a + b;
	}
	public void calcuate() {
		System.out.println(method(method(10.0,20.0),100));
	}
}
