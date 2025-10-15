package string;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/5/28 19:32
 */
public class Test {
    public static void main(String[] args) {
//        String s = "hello";
//        String str = "hello";
//        s.replace("l", "s");
//        String str1 = s;
//        String str2 = str;
//        System.out.println(s);
//        System.out.println(s==str);
//        System.out.println(s==str1);
//        System.out.println(s==str2);
//        System.out.println(str==str1);
        String a = "hello";
        String b = "abc";
        String c = a + b;
        String d = "hello" + b;
        String e = "hello" + "abc";
        System.out.println(c==d);
        System.out.println(c==e);
        System.out.println(d==e);
        System.out.println("****************");
        StringBuffer sb = new StringBuffer("abcdefghijklmn");
        System.out.println(sb);
        StringBuffer s = sb.append("abc");
        System.out.println(sb.toString());
        System.out.println(s==sb);
        System.out.println("***************");
        StringBuffer s3 = new StringBuffer("adfagadgfafa");

        System.out.println(s3);
        System.out.println(s3.substring(3,6));
    }
}
