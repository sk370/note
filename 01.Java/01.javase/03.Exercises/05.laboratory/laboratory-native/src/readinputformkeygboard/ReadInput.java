package readinputformkeygboard;

import java.util.Scanner;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/8/13 18:36
 */
public class ReadInput {
    public static void main(String[] args) {
        System.out.print("输入：");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        str = str.substring(str.lastIndexOf(" ") + 1);
        System.out.println(str.length());
    }
    @Test
    public void test01(){
        System.out.print("输入：");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        str = str.substring(str.lastIndexOf(" ") + 1);
        System.out.println(str.length());

    }
}
class ReadInput02 {
    public static void main(String[] args) {
        System.out.print("输入：");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        str = str.substring(str.lastIndexOf(" ") + 1);
        System.out.println(str.length());
    }
}