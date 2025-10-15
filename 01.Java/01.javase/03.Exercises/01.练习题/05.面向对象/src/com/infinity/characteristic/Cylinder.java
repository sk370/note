/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月16日上午11:31:43
 */
package com.infinity.characteristic;
// 继承
public class Cylinder extends Circle{
	private double length;
	public Cylinder() {
		length = 1.0;
	}
	public void setLength(double length) {
		this.length = length;
	}
	public double getLength() {
		return length;
	}
	public double findVolume() {
		return this.findArea() * length;
	}
	public static void main(String[] args) {
		Cylinder cc = new Cylinder();
		System.out.println(cc.findVolume());
	}
}
