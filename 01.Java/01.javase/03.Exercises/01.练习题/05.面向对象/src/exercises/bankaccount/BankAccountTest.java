/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月23日下午3:31:41
 */
package exercises.bankaccount;

public class BankAccountTest {
	public static void main(String[] args) {
		SavingAccount savingAccount = new SavingAccount(1000, 0.13);
		savingAccount.deposit(100);
		savingAccount.withdraw(100);
		savingAccount.deposit(100);
		savingAccount.deposit(100);
		System.out.println(savingAccount.getBalance());
		savingAccount.earnMonthlyInterest();
		savingAccount.deposit(100);
		System.out.println(savingAccount.getBalance());		
	}
}
