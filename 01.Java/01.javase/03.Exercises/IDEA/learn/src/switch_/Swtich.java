package switch_;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/7/13 23:24
 */
public class Swtich {
    public static void main(String[] args) {
        int n = 6;
        switch (n){
            case 1:
                System.out.println(1);
                break;
            case 2:
                System.out.println(2);
//                break;
            default:
                System.out.println(4);
//                break;
            case 3:
                System.out.println(3);
                break;
        }
    }
}
