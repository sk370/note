/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月11日上午11:56:42
 */
package com.infinity.erxrcise;

public class OutRectangle3 {
	public static void main(String[] args) {
		Rectangle3 r = new Rectangle3();
		int row = 10,col = 8;
		r.output(row, col);
		double area;
		area = r.area(row, col);
		System.out.println(area);
	}
}
class Rectangle3{
	public void output(int m,int n) {
		for (int i =1; i <= m; i++) {
			for (int j = 1;j <= n;j ++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
	public double area(int m,int n) {
		return m * n;
	}
}