/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月14日下午12:27:19
 */
package com.infinity.overload;

public class TriAngle {
	private double height;
	private double base;
	private double area;
	public void setHeight(double height) {
		this.height = height;
	}
	public double getHeight() {
		return this.height;
	}
	public double getBase() {
		return base;
	}
	public void setBase(double base) {
		this.base = base;
	}
	public void setArea(double height,double base) {
		this.base = base;
		this.height = height;
		this.area = this.base * this.height / 2;
	}
	public double getArea() {
		System.out.println(this.area);
		return this.area;
	}
	TriAngle(double height, double base){
		this.setArea(height, base);
//		this.getArea();
	}
}