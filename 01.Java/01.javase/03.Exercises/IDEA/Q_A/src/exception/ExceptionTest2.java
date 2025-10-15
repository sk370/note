package exception;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/5/26 15:45
 */
public class ExceptionTest2 {
    public static String output = "";

    public static void foo(int i) {
        try {
            if (i == 1)
                throw new Exception();
            output += "1";
        } catch (Exception e) {
            output += "2";
             return;
        } finally {
            output += "3";
        }
        output += "4";
    }

    public static void main(String args[]) {
        foo(0);
        System.out.println(output);//
        foo(1);
        System.out.println(output);//
    }
}
