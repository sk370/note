package string;

import java.util.Arrays;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/4 20:38
 */
public class ReseverserString {
    public static void main(String[] args) {
        String str = "abcdef";
        System.out.println(reverse(str, 1, 4));
    }
    public static String reverse(String str, int start, int end){
        char[] c = str.toCharArray();
        System.out.println(Arrays.toString(c));
        char temp = '0';
//        方式一：
//        for (int i = 0; i < (end - start) / 2 + 1; i++) {
//            temp = c[start + i];
//            c[start + i] = c[end - i];
//            c[end - i] = temp;
//        }
//        方式二：
        for (int i = start, j = end; i < j; i++, j--) {
            temp = c[i];
            c[i] = c[j];
            c[j] = temp;
        }
        return new String(c);
    }
}
