package innerclass;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/8 10:31
 */
public class TestAnonymous {
    public static void main(String[] args) {
        Test2.function().method();
    }
}
interface Inter{
    void method();
}
class Test2{
    public static Inter function(){
        return new Inter(){
            @Override
            public void method() {
                System.out.println("测试");
            }
        };
    }
}
