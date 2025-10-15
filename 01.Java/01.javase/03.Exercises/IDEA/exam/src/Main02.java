/**
 * @author zhuyuqi
 * @version v0.0.1
 * @className Main01
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/10/11 20:32
 */

import java.util.*;

public class Main02 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
//        int human = Integer.parseInt(in.nextLine());
        int human = in.nextInt();
        in.nextLine();
        String humanName = in.nextLine();
//        int ticketNum = Integer.parseInt(in.nextLine());
        int ticketNum = in.nextInt();
        in.nextLine();
        String tickets = in.nextLine();

        System.out.println(human);
        System.out.println(humanName);
        System.out.println(ticketNum);
        System.out.println(tickets);
    }
}
