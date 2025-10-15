/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月23日下午3:22:22
 */
package exercises.bankaccount;

public class CheckingAccount extends BankAccount {
	private int serviceCharge;
	public int getServiceCharge() {
		return serviceCharge;
	}
	public void setServiceCharge(int serviceCharge) {
		this.serviceCharge = serviceCharge;
	}
	public CheckingAccount() {
		super();
	}
	public CheckingAccount(double initialBalance, int serviceCharge) {
		super(initialBalance);
		this.serviceCharge = serviceCharge;
	}
	@Override
	public void deposit(double amount) {
		super.deposit(amount - serviceCharge);
	}
	@Override
	public void withdraw(double amount) {
		super.withdraw(amount + serviceCharge);
	}
}
