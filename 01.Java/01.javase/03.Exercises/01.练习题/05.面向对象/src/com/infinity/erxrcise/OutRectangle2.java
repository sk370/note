/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月11日上午11:56:42
 */
package com.infinity.erxrcise;

public class OutRectangle2 {
	public static void main(String[] args) {
		Rectangle2 r = new Rectangle2();
		r.output();
		double area;
		area = r.area();
		System.out.println(area);
	}
}
class Rectangle2{
	public void output() {
		for (int i =1; i <= 10; i++) {
			for (int j = 1;j <= 8;j ++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
	public double area() {
		return 10 * 8;
	}
}