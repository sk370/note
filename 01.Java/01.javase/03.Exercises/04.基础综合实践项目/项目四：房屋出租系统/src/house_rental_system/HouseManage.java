/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月23日下午4:13:23
 */
package house_rental_system;

import java.util.Scanner;

public class HouseManage extends HouseInfo{
	public HouseInfo[] houseInfo;
	public HouseInfo[] tempHouseInfo;
	public HouseInfo newHouseInfo = new HouseInfo(null, null, null, null, null);
	
	private int currentHouseNumber = 0;
	Scanner scanner = new Scanner(System.in);
	
	public void addHouseInfo() {
		currentHouseNumber++;
//		1. 创建了大小为currentHouseNumber的新数组，用来存放新增的房屋信息
		tempHouseInfo = new HouseInfo[currentHouseNumber];
		tempHouseInfo[currentHouseNumber-1] = new HouseInfo(null, null, null, null, null);
		System.out.println("-----------------添加房源信息-----------------");
		System.out.println("姓名：");
		tempHouseInfo[currentHouseNumber-1].setName(scanner.next());
		System.out.println("电话：");
		tempHouseInfo[currentHouseNumber-1].setPhone(scanner.next());
		System.out.println("地址：");
		tempHouseInfo[currentHouseNumber-1].setAddress(scanner.next());
		System.out.println("月租：");
		tempHouseInfo[currentHouseNumber-1].setMoney(scanner.next());
		while(true) {
			System.out.println("状态（出租/未出租）：");
			String str = scanner.next();
//			if(scanner.next().equals("出租") || scanner.next().equals("未出租") ) {//这样写有bug，会需要两次输入后才判断
			if(str.equals("出租") || str.equals("未出租") ) {
				tempHouseInfo[currentHouseNumber-1].setStatus(str);
				break;
			}else {
				System.out.println("输入不正确，请重新输入！");
			}
		}
//		1.1 将新增的房屋信息添加到新数组的最后一个元素
		if(houseInfo != null) {
			for(int i = 0; i < houseInfo.length; i++) {
				tempHouseInfo[i] = houseInfo[i];
			}
		}
//		2. 将旧数组重新指向新数组
		houseInfo = tempHouseInfo;
	}
	public void searchHouseInfo() {
		System.out.println("-----------------查找房源信息-----------------");
		if(currentHouseNumber == 0) {
			System.out.println("暂无房源信息");
			return;
		}
		System.out.println("请输入需要查找的房源序号：");
		int select = scanner.nextInt(); 
		System.out.println("查找结果如下：");
		System.out.println(select + "\t" + houseInfo[select].getName() + "\t" + houseInfo[select].getPhone() + "\t" + houseInfo[select].getAddress() + "\t" + houseInfo[select].getMoney() + "\t" + houseInfo[select].getStatus());
	}
	
	public void deleteHouseInfo() {
		System.out.println("-----------------删除房源信息-----------------");
		if(currentHouseNumber == 0) {
			System.out.println("暂无房源信息");
			return;
		}
		System.out.println("请输入需要删除的房源序号：");
		int select = scanner.nextInt(); 
		tempHouseInfo = new HouseInfo[currentHouseNumber - 1];
		for(int i = 0; i < tempHouseInfo.length; i++) {
			if(i < select) {
				tempHouseInfo[i] = houseInfo[i];
			}else {
				tempHouseInfo[i] = houseInfo[i + 1];
			}
		}
		currentHouseNumber--;
		houseInfo = tempHouseInfo;
	}
	
	public void changeHouseInfo() {
		System.out.println("-----------------更改房源信息-----------------");
		if(currentHouseNumber == 0) {
			System.out.println("暂无房源信息");
			return;
		}
		System.out.println("请输入需要修改的房源序号：");
		int select = scanner.nextInt(); 
		System.out.println("姓名" + houseInfo[select-1].getName() + ":");
		String n = scanner.next();
		if(n != "") {
			houseInfo[select-1].setName(n);
		}
		System.out.println("电话：" + houseInfo[select-1].getPhone() + ":");
		String p = scanner.next();
		if(p != "") {
			houseInfo[select-1].setName(p);
		}
		System.out.println("地址" + houseInfo[select-1].getAddress() + ":");
		String a = scanner.next();
		if(a != "") {
			houseInfo[select-1].setName(a);
		}
		System.out.println("月租" + houseInfo[select-1].getMoney() + ":");
		String m = scanner.next();
		if(m != "") {
			houseInfo[select-1].setName(m);
		}
	}
	
	public void showHouseInfo() {
		System.out.println("-----------------房屋列表-----------------");
		if(currentHouseNumber != 0) {
			System.out.println("序号\t名称\t电话\t地址\t价格\t状态");
			for(int i = 0; i < houseInfo.length; i++) {
				System.out.println((i + 1) + "\t" + houseInfo[i].getName() + "\t" + houseInfo[i].getPhone() + "\t" + houseInfo[i].getAddress() + "\t" + houseInfo[i].getMoney() + "\t" + houseInfo[i].getStatus());
			}
		}else {
			System.out.println("无房源信息！");
		}
		System.out.println("-----------------房屋列表完成-----------------");
	}
	
	public boolean exitHouseInfo() {
		System.out.println("确定退出吗？（Y/N）");
		char c = scanner.next().charAt(0);
		if(c == 'y' || c == 'Y') {
			return true;
		}else if(c == 'n' || c == 'N') {
			System.out.println("取消退出！");
			return false;
		}else {
			System.out.println("您的输入有误！");
			return false;
		}
	}
}
