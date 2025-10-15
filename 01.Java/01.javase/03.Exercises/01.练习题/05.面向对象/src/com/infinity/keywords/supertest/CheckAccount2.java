/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月16日下午5:00:05
 */
package com.infinity.keywords.supertest;

public class CheckAccount2 {
	public static void main(String[] args) {
		CheckAccount ca = new CheckAccount(1122, 20000, 0.045, 2000);
		ca.withdraw(5000);
		ca.withdraw(18000);
		ca.withdraw(3000);
	}
}
