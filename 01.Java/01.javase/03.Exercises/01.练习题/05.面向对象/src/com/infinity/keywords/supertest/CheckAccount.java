/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月16日下午3:53:58
 */
package com.infinity.keywords.supertest;

public class CheckAccount extends Account {
	private double overdraft = 5000;
	/**
	 * @Description
	 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
	 * @param id
	 * @param balance
	 * @param annualInterestRate
	 * @date 2022年5月16日下午4:21:21
	 */
	public CheckAccount(int id, double balance, double annualInterestRate, double overdraft) {
		super(id, balance, annualInterestRate);
		this.overdraft = overdraft;
	}
	public void withdraw (double amount) {
		/* 方式一
		double balance = this.getBalance();
		if(amount <= balance) {
			balance -= amount;
			setBalance(balance); 
			System.out.println("您的当前余额为：" + balance);
			System.out.println("可透支额度为：" + overdraft);
		}else if(amount - balance <= overdraft) {
			setBalance(0.0);
			System.out.println("您的当前余额为：" + balance);
			System.out.println("可透支额度为：" + (overdraft - (amount - balance)));
		}else {
			System.out.println("超过可透支限额：" );
		}
		*/
		// 方式二
		if(amount <= getBalance()) {
			super.withdraw(amount);
			System.out.println("您的当前余额为：" + getBalance());
			System.out.println("可透支额度为：" + overdraft);
		}else if(amount - getBalance() <= overdraft) {
			overdraft -= amount - getBalance();
			setBalance(0.0);
			System.out.println("您的当前余额为：0.0");
			System.out.println("可透支额度为：" + overdraft);
		}else {
			System.out.println("超过可透支限额!" );
		}
	}
}
