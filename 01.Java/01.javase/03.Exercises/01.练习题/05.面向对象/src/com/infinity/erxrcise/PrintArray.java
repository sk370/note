package com.infinity.erxrcise;
/**
 * @Description
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @version
 * @date 2022年5月11日上午10:02:43
 */
public class PrintArray {
	public static void main(String[] args) {
		int[][] arr = {{1},
				{1,2},
				{1,2,3}
		};
		ArrayTools arrayTools = new ArrayTools();
		arrayTools.printArray(arr);
	}
}
class ArrayTools{
	public void printArray(int[][] arr) {
		for(int i = 0;i < arr.length;i++) {
			for(int j = 0; j < arr[i].length; j++) {
				System.out.print(arr[i][j] + "\t");
			}
			System.out.println();
		}
	}
}