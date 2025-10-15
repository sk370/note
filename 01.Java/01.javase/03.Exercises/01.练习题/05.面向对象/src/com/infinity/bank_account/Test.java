/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月15日上午11:10:34
 */
package com.infinity.bank_account;

public class Test {
	public static void main(String[] args) {
		Customer cs = new Customer("Smith", "Jane");
		Account account = new Account(1000, 2000, 0.0123);
		cs.setAccount(account);
		cs.getAccount().deposits(100);
		cs.getAccount().withdraw(960);
		cs.getAccount().withdraw(2000);
		System.out.println(cs.getFirstName() + " " + cs.getLastName() + "的账户id为" + cs.getAccount().getId() + "，账户余额为" + cs.getAccount().getBalance() + "，利率为" + cs.getAccount().getAnnuallnterestRate());
	}
}
