package string;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/5 15:22
 */
public class GetFileName {
    public static void main(String[] args) {
        String s = "D:\\myfile\\hello.java";
        String str = s.substring(s.lastIndexOf("\\") + 1);
        System.out.println(str);
    }
}
