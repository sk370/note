/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月14日下午9:49:37
 */
package com.infinity.question_and_answer;

class Demo2{
	public static void main(String[] args){
		int x = 1;
		for(show('a'); show('b') && x<3; show('c')){
		show('d'); 
		x++;
		}
	}
public static boolean show(char ch){
	System.out.print(ch);
	return true;
	}
}