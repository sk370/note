/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月14日上午8:58:52
 */
package com.infinity.erxrcise;
public class Book {
	public double updatePrice(double n) {
		if(n > 150) {
			return 150.0;
		}else if(n > 100) {
			return 100.0;
		}else {
			return n;
		}
	}
	public static void main(String[] args) {
		Book book1 = new Book();
		Book book2 = new Book();
		Book book3 = new Book();
		System.out.println(book1.updatePrice(159.0));
		System.out.println(book2.updatePrice(123.0));
		System.out.println(book3.updatePrice(19.0));
	}
}
	