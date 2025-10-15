/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月12日下午3:57:43
 */
package com.infinity.question_and_answer;

public class TransferTest2 {
	public static void swap(DataSwap ds) {
		int temp = ds.a;
		ds.a = ds.b;
		ds.b = temp;
		System.out.println("swap方法里，a Field的值是" + ds.a + ";b Field的值是" + ds.b);
	}
	public static void main(String[] args) {
		DataSwap ds = new DataSwap();
		ds.a = 5;
		ds.b = 10;
		swap(ds);
		System.out.println("交换结束后，a Field的值是" + ds.a + ";b Field的值是" + ds.b);
	}
}
class DataSwap {
	public int a;
	public int b;
}
