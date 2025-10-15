/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月14日下午4:03:31
 */
package com.infinity.question_and_answer;
class Demo{
    int i = 100;
    public void m(){
        int j = i++;
        System.out.println("i = " + i);
        System.out.println("j = " + j);
    }
}
public class VariableOutputTest2{
	public static void main(String[] args){
        Demo d1 = new Demo();
        Demo d2 = new Demo();
        d2.m();
        System.out.println(d1.i);
        System.out.println(d2.i);
    }
}