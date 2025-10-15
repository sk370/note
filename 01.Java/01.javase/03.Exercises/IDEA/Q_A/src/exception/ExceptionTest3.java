package exception;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/5/26 16:04
 */
public class ExceptionTest3 {
    public static void main(String[] args) {
        int test = test(3,5);
        System.out.println(test);
    }
    public static int test(int x, int y){
        int result = x;
        try{
            if(x<0 || y<0){
                return 0;
            }
            result = x + y;
            return result;
        }finally{
            System.out.println(1);
            result = x - y;
//            return result;
        }
    }
}
