/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月26日下午6:44:50
 */
package exercises.animals;

public class SunFlower implements SeedPlant{

	@Override
	public void breath() {
		System.out.println("太阳花是植物，可以吸收二氧化碳，呼出氧气");
	}
	@Override
	public void reproduction() {
		System.out.println("太阳花可以用种子繁殖，也可以用枝条繁殖");		
	}
}
