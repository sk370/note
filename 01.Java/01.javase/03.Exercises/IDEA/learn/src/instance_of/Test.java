package instance_of;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/5/27 17:44
 */
public class Test {
}
class Test1 extends Test{
    public static void main(String[] args) {
        Test1 t1 = new Test1();
        System.out.println(t1 instanceof Test1);
        System.out.println(t1 instanceof Test);
        Test t = (Test)t1;
        System.out.println(t instanceof Test);
        System.out.println(t instanceof Test1);
    }
}
