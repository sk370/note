/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月14日下午10:10:20
 */
package com.infinity.question_and_answer;

public class Test1 {
	public static boolean foo(char c) {
		System.out.print(c);
		return true;
	}

	public static void main(String[] args) {
		int i = 0;
		for (foo('A'); foo('B') && (i < 2); foo('C')) {
			i++;// 1 2
			foo('D');
		}
	}
}
// A B D C B D C B