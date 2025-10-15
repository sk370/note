package enumeration;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/8 9:49
 */
public enum Weeks {
    MONDAY("打架"), TUESDAY("跳舞"), WENDSDAY("唱歌"),SAT("跳"), FIR("RAP"), THIR("打篮球"), SUN("鸡你太美");
    String plan;
    Weeks(String plan){
        this.plan = plan;
    }

    @Override
    public String toString() {
        return "Weeks{" +
                "plan='" + plan + '\'' +
                '}';
    }
}
class Test2{
    public static void main(String[] args) {
        Seasons[] seansons = Seasons.values();
        for (Seasons s : seansons){
            System.out.println(s + "\t" + s.name());
        }
    }
}
