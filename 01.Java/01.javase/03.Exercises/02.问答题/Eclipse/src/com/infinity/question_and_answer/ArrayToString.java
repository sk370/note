/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月19日上午10:59:04
 */
package com.infinity.question_and_answer;

public class ArrayToString {
	public void test() {
		char[] arr = new char[] { 'a', 'b', 'c' };
		System.out.println(arr);//
		System.out.println(arr.toString());//
		int[] arr1 = new int[] { 1, 2, 3 };
		System.out.println(arr1);//
		double[] arr2 = new double[] { 1.1, 2.2, 3.3 };
		System.out.println(arr2);//
	}
	public static void main(String[] args) {
		ArrayToString ats = new ArrayToString();
		ats.test();
	}
}
