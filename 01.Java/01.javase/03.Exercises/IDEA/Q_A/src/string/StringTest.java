package string;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/2 14:39
 */
public class StringTest {
    public static void main(String[] args) {
        String s1 = "dfafdafdadftdghgadf";
        String s2 = "hfasd";
        String s3 = s2.replace(s1,"x");
        int s4 = s1.indexOf("a",-1);
        System.out.println(s4);
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
    }
}
