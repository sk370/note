package string;

import java.util.Scanner;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/5 14:42
 */
public class ReverseOut {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入字符串：");
        String str = scanner.next();
        StringBuffer sb = new StringBuffer(str);
        System.out.println("共有字符" + (sb.length() + 1) + "个，反序输出为：" + sb.reverse());
    }
}
