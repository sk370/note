package string;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/4 17:13
 */
public class NumberFormat {
    public static void main(String[] args) {
        double num = 1234567.89;
        String temp = new String();
//        temp = num + "";
        temp = String.valueOf(num);
        StringBuffer target = new StringBuffer(temp);
        int index = target.indexOf(".");

        for (int i = index - 3; i > 0 ; i -= 3) {
           target.insert(i,",");
        }
        System.out.println(target);
    }

}
