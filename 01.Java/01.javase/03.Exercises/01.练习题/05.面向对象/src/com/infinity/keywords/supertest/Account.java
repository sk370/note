/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月16日下午3:33:55
 */
package com.infinity.keywords.supertest;

public class Account {
	private int id;
	private double balance;
	private double annualInterestRate;
//	public Account() {
//		
//	}
	/**
	 * @Description 构造账户
	 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
	 * @param id
	 * @param balance
	 * @param annualInterestRate
	 * @date 2022年5月16日下午3:46:17
	 */
	public Account(int id, double balance, double annualInterestRate) {
		this.id = id;
		this.balance = balance;
		this.annualInterestRate = annualInterestRate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public double getAnnualInterestRate() {
		return annualInterestRate;
	}
	public void setAnnualInterestRate(double annualInterestRate) {
		this.annualInterestRate = annualInterestRate;
	}
	public double getMonthlyInterest() {
		return this.annualInterestRate / 12;
	}
	public void deposit (double amount) {
		balance += amount;
		setBalance(balance);
		System.out.println("您的当前余额为：" + balance);
		System.out.println("月利率为：" + getMonthlyInterest());
	}
	public void withdraw (double amount) {
		if(amount > balance) {
			System.out.println("余额不足！");
			System.out.println("您的当前余额为：" + balance);
		}else {
			balance -= amount;
		}
	}
}
