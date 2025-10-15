/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月14日下午8:51:08
 */
package com.infinity.question_and_answer;

public class TransferTest3 {
	public static void main(String args[]) {
        TransferTest3 test = new TransferTest3();
        test.first();
    }
    public void first() {
        int i = 5;
        Value v = new Value();
        v.i = 25;
        second(v, i);
        System.out.println(v.i);
    }
    public void second(Value v, int i) {
        i = 0;
        v.i = 20;
        Value val = new Value();
        v = val;
        System.out.println(v.i + " " + i);
    } 
}
class Value {
    int i = 15;
}