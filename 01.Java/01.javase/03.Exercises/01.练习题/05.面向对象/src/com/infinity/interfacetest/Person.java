/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月25日上午11:44:47
 */
package com.infinity.interfacetest;

public class Person {
	private String name;
	private Vehicles v;
	public Person() {
		super();
	}
	public Person(String name, Vehicles v) {
		super();
		this.name = name;
		this.v = v;
		v.work();
	}
	public Vehicles river() {
		return VehiclesFactory.getBoat();
	}
	public static void main(String[] args) {
		Person person = new Person("唐僧", new Horse());
		person = new Person("汤森", person.river());
	}
}
interface Vehicles{
	void work();
}
class Horse implements Vehicles{
	public void work() {
		System.out.println("一般情况骑马");
	}
}
class Boat implements Vehicles{
	public void work() {
		System.out.println("遇河情况下坐船");
	}
}
class VehiclesFactory{
	public static Horse getHorse() {
		return new Horse();
	}
	public static Boat getBoat() {
		return new Boat();
	}
}