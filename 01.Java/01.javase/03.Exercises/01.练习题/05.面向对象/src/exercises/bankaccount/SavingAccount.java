/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月23日下午3:25:03
 */
package exercises.bankaccount;

public class SavingAccount extends BankAccount {
	private double interset;
	private int count = 0;
	public double getInterset() {
		return interset;
	}
	public void setInterset(double interset) {
		this.interset = interset;
	}
	public SavingAccount() {
		super();
	}
	public SavingAccount(double initialBalance, double interset) {
		super(initialBalance);
		this.interset = interset;
	}
	public double earnMonthlyInterest() {
		count = 0;
		return this.getBalance() + this.interset;
	}
	@Override
	public void deposit(double amount) {
		if(count >= 3)
			super.deposit(amount - 1);
		super.deposit(amount);
		count++;
	}
	@Override
	public void withdraw(double amount) {
		if(count >= 3)
			super.withdraw(amount + 1);
		super.withdraw(amount);
		count++;
	}
	
}
