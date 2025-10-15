/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月25日下午1:46:55
 */
package com.infinity.innerclass;

public class Car {
	public double temperature;	
	public Car(double temperature) {
		super();
		this.temperature = temperature;
	}
	class Air{
		public void flow() {
			if(temperature > 40.0) {
				System.out.println("吹冷气");
			}else if(temperature < 0){
				System.out.println("吹暖气");
			}else {
				System.out.println("关机");
			}
		}
	}
	public static void main(String[] args) {
//		1.逐步调用
		Car c1 = new Car(45);
		Car.Air air1 = c1.new Air();
		air1.flow();
//		2.省略Air实力调用
		Car c2 = new Car(-12);
		c2.new Air().flow();
//		3.匿名调用
		new Car(25).new Air().flow();
	}
}
