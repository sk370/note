/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月22日下午8:12:24
 */
package smallchange.oop;

import java.util.Scanner;

public class SmallChangeApp {
	public static void main(String[] args) {
		SmallChangeUI smallChangeUI = new SmallChangeUI();
		SmallChangeManage smallChangeManage = new SmallChangeManage();
		
		Scanner scanner = new Scanner(System.in);
		int input;
		
		while(smallChangeManage.loop) {
			smallChangeUI.showUI();	
			input = scanner.nextInt();
			switch (input){
				case 1:
					smallChangeManage.showDeatils();
					break;
				case 2:
					smallChangeManage.income();
					break;
				case 3:
					smallChangeManage.expenditure();
					break;
				case 4:
					smallChangeManage.exit();
			}
		}
	}
}
