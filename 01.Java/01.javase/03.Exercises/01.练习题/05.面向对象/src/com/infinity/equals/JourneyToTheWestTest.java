/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月19日下午6:09:12
 */
package com.infinity.equals;

public class JourneyToTheWestTest {
	public static void main(String[] args) {
		JourneyToTheWest tangseng = new JourneyToTheWest("唐僧", "人族");
		JourneyToTheWest sunwukong = new JourneyToTheWest("孙悟空", "猴族");
		JourneyToTheWest baigujing = new JourneyToTheWest("白骨精", "妖族");
		sunwukong.skill();
		System.out.println(sunwukong.toString());
//		System.out.println(sunwukong.equals());
		System.out.println("后族".equals(sunwukong));
	}
}
