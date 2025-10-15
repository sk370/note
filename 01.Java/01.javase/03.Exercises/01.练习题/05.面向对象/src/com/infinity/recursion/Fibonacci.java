/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月12日下午7:37:49
 */
package com.infinity.recursion;
public class Fibonacci {
	public static void main(String[] args) {
		Fibonacci f = new Fibonacci();
		System.out.println(f.fibonacci(10));
	}
	public int fibonacci(int a) {
		if(a == 1 || a == 2) {
			return 1;
		}else if(a <= 0) {
			System.out.println("输入有误！");
			return a;
		}else {
			return fibonacci(a - 2) + fibonacci(a - 1);
		}
	}
}
