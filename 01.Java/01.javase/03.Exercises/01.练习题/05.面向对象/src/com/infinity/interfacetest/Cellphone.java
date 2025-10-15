/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月24日下午9:25:26
 */
package com.infinity.interfacetest;

public class Cellphone{
	// 方式一：
	/*
	 * public Calculator testWork(Calculator c) { return c; } public static void
	 * main(String[] args) { Cellphone cp = new Cellphone(); double a =
	 * cp.testWork(new Calculator() {
	 * 
	 * @Override public double work() { return 1 + 1; } }).work();
	 * System.out.println(a); }
	 */
	// 方式二：
	public double  testWork(Calculator c) {
		double result;
		result = c.work();
		return result;
	}
	public static void main(String[] args) {
		Cellphone cp = new Cellphone();
		double a = cp.testWork(new Calculator() {
			@Override
			public double work() {
				return 1 + 1;
			}
		});
		System.out.println(a);
	}
}
