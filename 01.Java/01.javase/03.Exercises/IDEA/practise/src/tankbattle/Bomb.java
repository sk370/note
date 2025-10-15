package tankbattle;

/**
 * @desc 击中爆炸效果，每个坦克可以击中9下
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/10 18:51
 */
public class Bomb {
    int x, y;
    int life = 9;
    boolean isLive = true;

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void lifeDown(){
        if(life > 0){
            life--;
        }else {
            isLive = false;
        }
    }

}
