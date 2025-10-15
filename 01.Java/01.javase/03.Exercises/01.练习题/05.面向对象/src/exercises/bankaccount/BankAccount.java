/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月23日下午3:14:22
 */
package exercises.bankaccount;

public class BankAccount {
	private double balance;
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public BankAccount() {
		super();
	}
	public BankAccount(double initialBalance) {
		super();
		this.balance = initialBalance;
	}
	public void deposit(double amount) {
		balance += amount;
	}
	public void withdraw(double amount) {
		balance -= amount;
	}	
}
