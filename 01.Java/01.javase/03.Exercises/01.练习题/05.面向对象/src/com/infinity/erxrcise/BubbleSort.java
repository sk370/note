/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月17日上午11:59:45
 */
package com.infinity.erxrcise;

import java.util.Arrays;

public class BubbleSort {
	public static void main(String[] args){
		int[] array = {3,2,5,1,7};
		BubbleSort bs = new BubbleSort();
		bs.sort(array);
		System.out.println("排序后的结果是：" + Arrays.toString(array));
	}
	//冒泡方法，从大到小
	public void sort(int[] arr){
		int temp = arr[0];
		for(int i = 0; i < arr.length - 1;i ++ ) {
			for(int j = 0; j < arr.length - i - 1; j++) {
				if(arr[j] < arr[j+1]) {
					temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
	}
		
}