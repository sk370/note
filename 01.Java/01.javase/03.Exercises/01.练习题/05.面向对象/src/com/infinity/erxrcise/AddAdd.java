/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月18日上午7:47:45
 */
package com.infinity.erxrcise;

public class AddAdd {
	public static void main(String[] args) {
		int i = 0;
		change(i);
		i = i++;
		System.out.println("i = " + i);
	}

	public static void change(int i) {
		i++;
	}
}
