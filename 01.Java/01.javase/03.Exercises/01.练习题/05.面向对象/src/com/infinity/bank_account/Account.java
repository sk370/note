/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月15日上午9:55:13
 */
package com.infinity.bank_account;

public class Account {
	private int id;
	private double balance;
	private double annuallnterestRate;
	public Account(int id, double balance, double annuallnterestRate) {
		this.id = id;
		this.balance = balance;
		this.annuallnterestRate = annuallnterestRate;
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
	public double getAnnuallnterestRate() {
		return annuallnterestRate;
	}
	public void setAnnuallnterestRate(double annuallnterestRate) {
		this.annuallnterestRate = annuallnterestRate;
	}
	public void withdraw(double amount) {
		if(this.balance > 0 && this.balance > amount) {
			this.balance -= amount;
			System.out.println("成功取出" + amount + "元！" + "您的账户还剩余：" + balance + "元！");
		}else {
			System.out.println("请检查账户余额！");
		}
	}
	public void deposits(double amount) {
		this.balance += amount;
		System.out.println("成功存入" + amount + "元！当前账户余额为：" + balance + "元！");
	}
}
