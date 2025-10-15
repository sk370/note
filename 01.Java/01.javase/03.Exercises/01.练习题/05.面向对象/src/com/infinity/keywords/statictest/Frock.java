/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月24日下午7:12:36
 */
package com.infinity.keywords.statictest;

/*public class Frock {
	private static int currentNum = 10000;
	private int serialNumber;
	public int getSerialNumber() {
		return serialNumber;
	}
	public Frock() {
		super();
		this.serialNumber = getNextNum();
	}
	public static int getNextNum() {
		currentNum += 100;
		return currentNum;
	}
}*/

public class Frock {
	private static int currentNum = 10000;
//	private int serialNumber;
//	public int getSerialNumber() {
//		return serialNumber;
//	}
	public Frock() {
		super();
		currentNum += 100;
//		this.serialNumber = getNextNum();
	}
	public static int getCurrentNum() {
		return currentNum;
	}
	
//	public static int getNextNum() {
//		currentNum += 100;
//		return currentNum;
//	}
}