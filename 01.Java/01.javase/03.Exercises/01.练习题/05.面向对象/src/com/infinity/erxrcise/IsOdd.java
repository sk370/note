package com.infinity.erxrcise;

/**
 * @Description
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @version
 * @date 2022年5月11日上午9:43:57
 */
public class IsOdd {
	public static void main(String[] args) {
		int n = 8;
		AA aa = new AA();
		if(aa.isOdd(n)) {
			System.out.println(n + "是偶数");
		}else{
			System.out.println(n + "是奇数");
		};
	}
}
class AA{
	public boolean isOdd(int n) {
		if(n%2==0) {
			return true;
		}else {
			return false;
		}
	}
}