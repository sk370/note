/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月11日下午12:07:16
 */
package com.infinity.erxrcise;

public class StudentArray {
	public static void main(String[] args) {
		int num = 20;
		Student[] stu = new Student[num];
		Student temp = new Student();
		for(int i = 0;i < stu.length; i++) {
			stu[i] = new Student();
			stu[i].number = i + 1;
			stu[i].state = (int)(Math.random() * 6 + 1);//1-6
			stu[i].score = (int)(Math.random() * 101);//0-100
			if(stu[i].state == 3) {
				System.out.println("年级是三年级的学生学号是：" + stu[i].number + ",分数是：" + stu[i].score);
			}
		}
		for(int i = 0;i < stu.length - 1; i++) {
			for(int j = 0;j < stu.length - i - 1; j++) {
				if(stu[j].score <= stu[j+1].score) {
					temp = stu[j];
					stu[j] = stu[j+1];
					stu[j+1] = temp;
				}
			}
		}
		for(int i = 0;i < stu.length; i++) {
			System.out.println("学号是：" + stu[i].number + "的学生年级是" + stu[i].state +  ",分数是：" + stu[i].score);
		}
	}
}
class Student{
	int number;
	int state;
	int score;
}