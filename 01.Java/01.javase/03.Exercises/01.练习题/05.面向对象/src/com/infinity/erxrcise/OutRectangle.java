/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月11日上午11:56:42
 */
package com.infinity.erxrcise;

public class OutRectangle {
	public static void main(String[] args) {
		Rectangle r = new Rectangle();
		r.output();
	}
}
class Rectangle{
	public void output() {
		for (int i =1; i <= 10; i++) {
			for (int j = 1;j <= 8;j ++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
}