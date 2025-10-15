/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月12日下午5:58:58
 */
package com.infinity.overload;
public class MOL {
	public static void main(String[] args) {
		MOL mol = new MOL();
		mol.mOL(2);
		mol.mOL(2, 3);
		mol.mOL("今天是2022年5月12日");		
	}
	public void mOL(int a) {
		System.out.println(a * a);
	}
	public void mOL(int a, int b) {
		System.out.println(a * b);
	}
	public void mOL(String s) {
		System.out.println(s);
	}
}
