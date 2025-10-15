package teammanage.service;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/5/27 10:20
 */
public class Status {
    private final String NAME;

    public Status(String name) {
        this.NAME = name;
    }

    public String getNAME() {
        return NAME;
    }

    @Override
    public String toString() {
        return NAME;
    }

    public static final Status FREE = new Status("FREE");
    public static final Status BUSY = new Status("BUSY");
    public static final Status VOCATION = new Status("VOCATION");
}
