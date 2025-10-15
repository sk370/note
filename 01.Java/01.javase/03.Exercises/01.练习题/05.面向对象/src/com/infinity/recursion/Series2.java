/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月12日下午6:27:53
 */
package com.infinity.recursion;
public class Series2 {
	public static void main(String[] args) {
		Series2 s = new Series2();
		int result = s.series(10);
		System.out.println(result);
	}
	public int series(int a) {
		if(a == 20) {
			return 1;
		}else if(a == 21) {
			return 4;
		}else if(a < 20){
			return series(a + 2) - 2 * series(a + 1);
		}else{
			return 2 * series(a - 1) + series(a - 2);
		}
	}
}
