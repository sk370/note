/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月14日下午4:03:31
 */
package com.infinity.question_and_answer;
public class VariableOutputTest1{
    int count = 9;
    public void count1(){
        count = 10;
        System.out.println("count1=" + count);
    }
    public void count2(){
        System.out.println("count2=" + count++);
    }
    public static void main(String[] args){
        new VariableOutputTest1().count1();
        VariableOutputTest1 t1 = new VariableOutputTest1();
        t1.count2();
        t1.count2();
    }
}
	
