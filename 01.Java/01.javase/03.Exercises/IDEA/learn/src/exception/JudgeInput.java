package exception;

import java.util.Scanner;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/5/26 10:57
 */
public class JudgeInput {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int input;
        String str;
        while (true){
            System.out.print("请输入：");
            str = scanner.next();
            try {
                input = Integer.parseInt(str);
                break;
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(input);
    }
}
