/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月26日下午6:43:31
 */
package exercises.animals;

public class Platypus implements Mammal{

	@Override
	public void breath() {
		System.out.println("鸭嘴兽是动物，需要呼吸氧气");
	}
	@Override
	public void run() {
		System.out.println("鸭嘴兽只有两只脚，它可以用两只脚奔跑");		
	}
}
