package string;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/5 14:36
 */
public class OutputUppercase {
    public static void main(String[] args) {
        int count = 0;
        String str = "DdfafdaDFAJLJsdJJFLJAdj;l";
        System.out.println(str);
        if(str.charAt(0) > 'A' && str.charAt(0) < 'Z'){
            char[] c = str.toCharArray();
            for (int i = 1; i < c.length; i++) {
                if(c[i] > 'A' && c[i] < 'Z'){
                    count++;
                }
            }
            System.out.println("共有大写字母：" + (count + 1) + "个");
        }else {
            System.out.println("第一个字母不是大写！");
        }
    }
}
