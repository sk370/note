package com.infinity.erxrcise;
/**
 * @Description
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @version
 * @date 2022年5月11日上午9:55:49
 */
public class Print {
	public static void main(String[] args) {
		Tools tools = new Tools();
		tools.print(4, 4, '#');
	}
}
class Tools{
	public void print(int x, int y, char n){
		for(int i = 1;i <= x;i++) {
			for(int j = 1; j <= y; j++) {
				System.out.print(n);
			}
			System.out.println();
		}
	}
}