/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月12日下午6:10:32
 */
package com.infinity.overload;
public class Calculate {
	public static void main(String[] args) {
		Calculate c = new Calculate();
		System.out.println(c.max(2, 3));
		System.out.println(c.max(2.3, 2.0));
		System.out.println(c.max(8.9, 9.0, 9.2));		
	}
	public int max(int a, int b) {
		return (a > b) ? a : b;
	}
	public double max(double a, double b) {
		return (a > b) ? a : b;
	}
	public double max(double a, double b, double c) {
		double temp = max(a, b);
		return (temp > c) ? temp : c;
	}
}
