/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月24日下午9:16:23
 */
package com.infinity.keywords.abstracttest;

public class AnimalTest {
	public static void main(String[] args) {
		Animal cat = new Cat();
		Animal dog = new Dog();
		cat.shout();
		dog.shout();
		Cat cat1 = new Cat();
		Dog dog1 = new Dog();
		cat1.shout();
		dog1.shout();
		
	}
}
