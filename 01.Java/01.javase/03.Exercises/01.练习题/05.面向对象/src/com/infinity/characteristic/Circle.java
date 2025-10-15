/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月16日上午11:27:32
 */
package com.infinity.characteristic;

public class Circle {
	private double radius;
	public Circle() {
		radius = 1.0;
	}
	public Circle(double radius) {
		this.radius = radius;
	}
	public void setRadius(double radius) {
		this.radius = radius;
	}
	public double getRadius() {
		return radius;
	}
	public double findArea() {
		return radius * radius * 3.14;
	}
}
