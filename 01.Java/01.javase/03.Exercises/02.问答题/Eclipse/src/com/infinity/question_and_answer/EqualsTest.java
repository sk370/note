/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月19日上午9:59:29
 */
package com.infinity.question_and_answer;

public class EqualsTest {
	public static void main(String[] args) {
		Date m1 = new Date(14, 3, 1976);
		Date m2 = new Date(14, 3, 1976);
		if (m1 == m2) {
			System.out.println("m1==m2");
		} else {
			System.out.println("m1!=m2"); // m1 != m2
		}
		if (m1.equals(m2)) {
			System.out.println("m1 is equal to m2");// m1 is equal to m2
		} else {
			System.out.println("m1 is not equal to m2");
		}
	}
}
class Date{
	int day;
	int month;
	int year;
	Date(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj != null) {
			if(obj instanceof Date) {
				Date Date = (Date)obj;
				return this.day == Date.day && this.month == Date.month && this.year == Date.year;
			}
		}
		
		return false;
	}
}