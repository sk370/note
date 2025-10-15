/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月14日下午9:41:40
 */
package com.infinity.question_and_answer;

public class Demo1{
    public static void main(String[] args){
    show(0);
    show(1);
}
public static void show(int i){
    switch(i){
    default:
        i+=2;
    case 1:
        i+=1;
    case 4:
        i+=8;
    case 2:
        i+=4;
    }
    System.out.println("i="+i);
    }
}