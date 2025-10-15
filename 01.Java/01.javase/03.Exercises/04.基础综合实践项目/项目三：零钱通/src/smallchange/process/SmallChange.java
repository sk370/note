/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月22日下午5:09:57
 */
package smallchange.process;

import java.text.DateFormat;
import java.util.Date;
import java.util.Scanner;

public class SmallChange {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean loop = true;
		int input = 0;
		String details = "";
		double money = 0.0;
		Date currtentTime = new Date();
		DateFormat df = DateFormat.getDateInstance();
		double balance = 0.0;
		String note = null;
		char exit = '0';
		while(loop) {
			System.out.println("-----------------零钱通菜单-----------------");
			System.out.println("               1 零钱通明细");
			System.out.println("               2 收益 入账");
			System.out.println("               3 消    费");
			System.out.println("               4 退    出");
			System.out.print("请选择1-4：");
			input = scanner.nextInt();
			switch (input){
				case 1:
					System.out.println("-----------------零钱通明细-----------------");
					System.out.println(details);
					break;
				case 2:
					System.out.println("-----------------收益 入账-----------------");
					for(;;) {
						currtentTime = new Date();
						System.out.print("请输入收入金额：");
						money = scanner.nextDouble();
						if(money < 0) {
							System.out.println("收入不能为负数");
						}else {
							balance += money;
							details += "收入\t+" + money + "\t" + df.format(currtentTime) + "\t" + balance + "\n";
							break;
						}
					}
					break;
				case 3:
					System.out.println("-----------------消    费-----------------");
					currtentTime = new Date();
					System.out.print("请输入支出项目：");
					note = scanner.next();
					for(;;) {
						System.out.print("请输入支出金额：");
						money = scanner.nextDouble();
						if(money < 0) {
							System.out.println("支出不能为负数！");
						}else if(money > balance) {
							System.out.println("余额不足！");
						}else {
							balance -= money;
							details += note + "\t-" + money + "\t" + df.format(currtentTime) + "\t" + balance + "\n";
							break;
						}
					}
					break;
				case 4:
					System.out.println("确认退出吗？（Y/N）");
					while(true) {
						exit = scanner.next().charAt(0);
						if(exit == 'y' || exit == 'Y') {
							loop = false;
							System.out.println("退出成功！");
							break;
						}else if(exit == 'n' || exit == 'N') {
							break;
						}
						System.out.println("你的输入有误，请重新输入！");
					}
			}
		}
	}
}
