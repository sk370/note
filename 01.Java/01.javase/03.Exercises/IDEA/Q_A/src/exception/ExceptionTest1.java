package exception;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/5/26 15:35
 */
public class ExceptionTest1 {
    public static void func() throws Exception{
        try {
            throw new Exception();
        } finally {
            System.out.println("B");
        }
    }

    public static void main(String[] args) {
        try {
            func();
            System.out.println("A");
        } catch (Exception e) {
            System.out.println("C");
        }
        System.out.println("D");
    }
}

