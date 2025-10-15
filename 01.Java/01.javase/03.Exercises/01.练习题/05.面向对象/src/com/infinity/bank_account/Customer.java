/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月15日上午11:05:54
 */
package com.infinity.bank_account;

public class Customer {
	private String firstName;
	private String lastName;
	private Account account;
	public Customer() {
		
	}
	public Customer(String f, String l){
		this.firstName = f;
		this.lastName = l;
	}
	public String getFirstName() {
		return this.firstName;
	}
	public String getLastName() {
		return this.lastName;
	}
	public Account getAccount() {
		return this.account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
}
