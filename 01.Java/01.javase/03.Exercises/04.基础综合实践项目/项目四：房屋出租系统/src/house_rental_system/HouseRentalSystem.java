/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月23日下午4:11:52
 */
package house_rental_system;

import java.util.Scanner;

public class HouseRentalSystem {
	public static void main(String[] args) {
		HouserUI houseUI = new HouserUI();
		HouseManage houseManage = new HouseManage();
		Scanner scanner = new Scanner(System.in);
		int input;
		do {
			houseUI.printUI();
			input = scanner.nextInt();
			switch (input) {
				case 1:
					houseManage.addHouseInfo();
					break;
				case 2:
					houseManage.searchHouseInfo();
					break;
				case 3: 
					houseManage.deleteHouseInfo();
					break;
				case 4:
					houseManage.changeHouseInfo();
					break;
				case 5:
					houseManage.showHouseInfo();
					break;
				case 6:
					if(houseManage.exitHouseInfo()) {;
						System.out.println("退出成功！");
						return;
					}
			}
		}while(true);
	}
}
