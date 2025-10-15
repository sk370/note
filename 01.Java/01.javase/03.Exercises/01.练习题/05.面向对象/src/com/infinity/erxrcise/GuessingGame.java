/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月14日上午10:55:34
 */
package com.infinity.erxrcise;
import java.util.Scanner;
// 0 石头 1剪刀 2 布
public class GuessingGame {
	int num, pc, person;
	public int generateNum() {
		num = (int)(Math.random() * 3);
		System.out.println("电脑出圈为：" + num);
		return num;
	}
	public void judge(int num) {
		if(this.num == num) {
			System.out.println("平局！");
		}else if(this.num  == 0) {
			if(num == 1) {
				System.out.println("电脑赢！");
				pc++;
			}else if(num == 2) {
				System.out.println("玩家赢！");
				person++;
			}
		}else if(this.num == 1) {
			if(num == 0) {
				System.out.println("玩家赢！");
				person++;
			}else if(num == 2) {
				System.out.println("电脑赢！");
				pc++;
			}
		}else if(this.num == 2) {
			if(num == 1) {
				System.out.println("玩家赢！");
				person++;
			}else if(num == 0) {
				System.out.println("电脑赢！");
				pc++;
			}
		}
	}
	public void frequency() {
		System.out.println("玩家赢次数为：" + person + "，电脑赢次数为：" + pc);
	}
	public static void main(String[] args) {
		GuessingGame gg = new GuessingGame();
		Scanner scan = new Scanner(System.in);
		int num;
		while(true) {
			gg.generateNum();
			System.out.println("电脑已出，该玩家出！");
			num = scan.nextInt();
			gg.judge(num);
			gg.frequency();
		}
	}
}
