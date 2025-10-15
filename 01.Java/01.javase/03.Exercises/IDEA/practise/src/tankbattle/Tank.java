package tankbattle;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/9 10:03
 */
public class Tank {
    private int x;
    private int y;
    private int direct;
    private int speed = 1;

    /**
     * @desc 坦克向上移动
     */
    public void moveUp(){
        y -= speed;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * @desc 坦克向下移动
     */
    public void moveDown(){
        y += speed;
    }
    /**
     * @desc 坦克向右移动
     */
    public void moveRight(){
        x += speed;
    }
    /**
     * @desc 坦克向左移动
     */
    public void moveLeft(){
        x -= speed;
    }

    public Tank(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
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
}
