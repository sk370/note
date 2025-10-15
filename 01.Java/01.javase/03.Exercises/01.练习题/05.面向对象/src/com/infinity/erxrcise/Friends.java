/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月11日下午1:44:39
 */
package com.infinity.erxrcise;

public class Friends {
	public static void main(String[] args) {
		Date me = new Date();
		Date friends = new Date();
		me.year = 1994;
		me.month = 11;
		me.day = 15;
		friends.year = 1994;
		friends.month = 12;
		friends.day = 26;
		System.out.println(me.year + "\t" + me.month + "\t" + me.day);
		System.out.println(friends.year + "\t" + friends.month + "\t" + friends.day);
	}
}
class Date{
	int year;
	int month;
	int day;
}