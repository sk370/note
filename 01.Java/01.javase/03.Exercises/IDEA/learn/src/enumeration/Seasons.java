package enumeration;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/8 8:46
 */
public enum Seasons {
    SPRING, SUMMER, AUTUMN, WINTER;

    Seasons() {
    }
}
class Test1{
    public static void main(String[] args) {
        Seasons[] s = Seasons.values();
        System.out.println(s);
    }
}