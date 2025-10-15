package tankbattle;

/**
 * @desc 用来保存读取到的坦克信息
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/14 13:17
 */
public class Node {
    private int x;
    private int y;
    private int d;

    public Node(int x, int y, int d) {
        this.x = x;
        this.y = y;
        this.d = d;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }
}
