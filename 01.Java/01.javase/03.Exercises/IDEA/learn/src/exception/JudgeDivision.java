package exception;

import java.util.Scanner;

/**
 * @desc: 编写应用程序，接收命令行的两个参数(整数)，计算两数相除。计算两个数相除，要求使用方法 cal(int n1, int n2),对数据格式不正确(NumberFormatException)、除0进行异常处理(ArithmeticException)。
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/5/26 12:53
 */
public class JudgeDivision {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a, b, res = 0;
        try {
            System.out.print("请输入第一个数：");
            a = Integer.parseInt(scanner.next());
            System.out.print("请输入第二个数：");
            b = Integer.parseInt(scanner.next());
            res = cal(a, b);
            System.out.println(res);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        } catch (ArithmeticException e){
            System.out.println(e.getMessage());
            System.out.println("除0异常");
        }

//        System.out.println(12.0/0.0);
//        System.out.println(12.0/0);
//        System.out.println(12/0.0);
//        System.out.println(12 / 0);
    }
    static int cal(int n1, int n2){
        return n1 / n2;
    }
}
