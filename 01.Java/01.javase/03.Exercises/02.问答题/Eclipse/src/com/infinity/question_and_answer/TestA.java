/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月17日上午11:40:35
 */
package com.infinity.question_and_answer;

public class TestA {
    int i ;
    void change(int i){
        i++;
        System.out.println(i);
    }
    void change1(TestA t){
        t.i++;
        System.out.println(t.i);
    }
    public static void main(String[] args) {
        TestA ta = new TestA();
        System.out.println(ta.i); 
        ta.change(ta.i);
        System.out.println(ta.i); 
        ta.change1(ta);  
        System.out.println(ta.i);
    }
}