/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月12日下午6:27:53
 */
package com.infinity.recursion;
public class Series {
	public static void main(String[] args) {
		Series s = new Series();
		int result = s.series(10);
		System.out.println(result);
	}
	public int series(int a) {
		if(a == 0) {
			return 1;
		}else if(a == 1) {
			return 4;
		}else if(a < 0){
			System.out.println("输入有误！");
			return a;
		}else {
			return 2 * series(a - 1) + series(a -2);
		}
	}
}
