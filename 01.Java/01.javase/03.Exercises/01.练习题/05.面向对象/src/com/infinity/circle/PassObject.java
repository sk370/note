/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月12日下午5:11:24
 */
package com.infinity.circle;
import java.util.Scanner;
public class PassObject {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("输入一个半径值：");
		double r = scanner.nextDouble();
		PassObject po = new PassObject();
		Circle circle = new Circle();
		po.printAreas(circle, r);

	}
	public void printAreas(Circle c, double time) {
		System.out.println("Radius\tArea");
		for(int i = 1; i <= time; i++) {
			c.radius = i;
			System.out.println(i + "\t" + c.findArea());
		}
		System.out.println("当前的半径值是：" + (c.radius + 1));
	}
}