/**
 * @author zhuyuqi
 * @version v0.0.1
 * @className Main01
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/10/11 20:32
 */
public class Main01 {
//    斐波那契数列
//    写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：
//    F(0) = 0,   F(1) = 1
//    F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
//    斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
//    示例 1：
//    输入：n = 2
//    输出：1
//    示例 2：
//    输入：n = 5
//    输出：5
//    提示：0 <= n <= 100
    public int fibonacci(int n){
        if(n == 0){
            return 0;
        }else if(n ==1 ){
            return 1;
        }
        return fibonacci(n-1) + fibonacci((n -2));
    }
}
