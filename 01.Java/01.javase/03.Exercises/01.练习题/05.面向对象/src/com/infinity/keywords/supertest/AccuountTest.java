/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月16日下午3:43:05
 */
package com.infinity.keywords.supertest;

public class AccuountTest {
	public static void main(String[] args) {
		Account at = new Account(1122, 20000, 0.045);
		at.withdraw(30000);
		at.withdraw(2500);
		at.deposit(3000);
	}
}
