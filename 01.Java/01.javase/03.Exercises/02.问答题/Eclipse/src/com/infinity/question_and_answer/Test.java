/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月17日上午11:51:29
 */
package com.infinity.question_and_answer;

public class Test {
    int x= 12;
    public void method(int x) {
         x+=x;
         System.out.println(x);
         System.out.println(this.x);
         this.x += x;
         System.out.println(x);
         System.out.println(this.x);
    }
    public static void main(String[] args) {
    	Test t = new Test();
    	t.method(5);
	}
}
