/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月11日上午11:52:01
 */
package com.infinity.erxrcise;

public class CalculateArea {
	public static void main(String[] args) {
		int radius = 10;
		Circle c = new Circle();
		double area = c.calculateArea(radius);
		System.out.println("面积为：" + area + "平方米");
	}
}
class Circle{
	public double calculateArea(int r) {
		return 3.14 * r * r;
	}
}