package enumeration;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/5/24 14:02
 */
public class Week01{
    public static void main(String[] args) {
        Week[] week = Week.values();
        for(Week s : week){
            System.out.println(s);
            System.out.println(s.name());
        }
    }
}
enum Week {
    MONDAY("星期一"), TUESDAY("星期二"), WEDNESDAY("星期三"), THURSDAY("星期四"), FRIDAY("星期五"), SATURDAY("星期六"), SUNDAY("星期日");
    private String name;
    Week(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
