/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月22日下午8:15:01
 */
package smallchange.oop;

import java.text.DateFormat;
import java.util.Date;
import java.util.Scanner;

public class SmallChangeManage {
	boolean loop = true;
	String details = "";
	double money = 0.0;
	Date currtentTime = new Date();
	DateFormat df = DateFormat.getDateInstance();
	double balance = 0.0;
	String note = null;
	char exit = '0';
	Scanner scanner = new Scanner(System.in);
	
	public void showDeatils() {
		System.out.println("-----------------零钱通明细-----------------");
		System.out.println(details);
	}
	
	public String income() {
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
		return details;
	}
	
	public String expenditure() {
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
		return details;
	}
	
	public void exit() {
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
