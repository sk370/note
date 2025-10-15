package compare;

import java.util.Arrays;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/5 15:27
 */
public class CharSort {
    public static void main(String[] args) {
        String str = "fakjoi1jio2j3jljdofaj";
        char[] c = str.toCharArray();
        Arrays.sort(c);
        String s = new String(c);
        System.out.println(s);
    }
}
