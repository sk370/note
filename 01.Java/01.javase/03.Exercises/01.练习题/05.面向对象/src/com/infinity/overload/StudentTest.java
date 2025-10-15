/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月14日下午2:47:46
 */
package com.infinity.overload;
public class StudentTest {
	public static void main(String[] args) {
		Student s1 = new Student("小tom", 10);
		Student s2 = new Student("大tom", 12, "吊湾中学");
		Student s3 = new Student("大大tom", 15, "秦安一中", "高一");
		System.out.println(s1.getName() + "," + s1.getAge() + "," + s1.getSchool() + "," + s1.getMajor());
		System.out.println(s2.getName() + "," + s2.getAge() + "," + s2.getSchool() + "," + s2.getMajor());
		System.out.println(s3.getName() + "," + s3.getAge() + "," + s3.getSchool() + "," + s3.getMajor());
	}
}
