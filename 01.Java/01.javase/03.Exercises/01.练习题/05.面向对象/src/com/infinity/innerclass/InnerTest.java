/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月25日上午8:54:34
 */
package com.infinity.innerclass;

public class InnerTest {
	public static void main(String[] args) {
		Outer o = new Outer();
		o.ma();
	}
}
class Outer{
	private int s;
	public class Inner{
		public void mb() {
			s = 100;
			System.out.println(s);
		}
	}
	public void ma() {
		Inner i = new Inner();
		i.mb();
	}
}
