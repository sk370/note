/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月19日下午5:17:36
 */
package com.infinity.equals;

import java.util.Objects;

public class Car {
	private String type;
	private String color;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	public Car() {
		super();
	}
	public Car(String type, String color) {
		super();
		this.type = type;
		this.color = color;
	}
	@Override
	public String toString() {
		return "Car [type=" + type + ", color=" + color + "]";
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		return Objects.equals(color, other.color) && Objects.equals(type, other.type);
	}
	public static void main(String[] args) {
		Car car1 = new Car("东风", "white");
		Car car2 = new Car("本田", "white");
		Car car3 = new Car("东风", "white");
		System.out.println(car1);
		System.out.println(car1.equals(car3));
		System.out.println(car1.equals(car2));
	}
}
