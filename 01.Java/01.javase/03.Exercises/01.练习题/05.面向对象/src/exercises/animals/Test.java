/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月26日下午6:54:17
 */
package exercises.animals;

public class Test {
	public static void main(String[] args) {
		Animal platypus = new Platypus();
		SeedPlant sunFlower = new SunFlower();
		platypus.breath();
		platypus.run();
		sunFlower.breath();
		sunFlower.reproduction();
	}
}
