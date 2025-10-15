package tankbattle;

import java.util.Vector;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/9 10:09
 */
public class HeroTank extends Tank{
    Shot shot = null;
    Vector<Shot> shots = new Vector<>();
    boolean isLive = true;
    public HeroTank(int x, int y, int direct) {
        super(x, y, direct);
    }
    public void shotEnemyTank(){
//        己方子弹最多同时存在5颗
        if(!isLive || shots.size() > 5){
            return;
        }
        switch (this.getDirect()){
            case 0:
                shot = new Shot(this.getX() + 20, this.getY(), this.getDirect());
                break;
            case 1:
                shot = new Shot(this.getX() + 60, this.getY() + 20, this.getDirect());
                break;
            case 2:
                shot = new Shot(this.getX() + 20 , this.getY() + 60, this.getDirect());
                break;
            case 3:
                shot = new Shot(this.getX() , this.getY() + 20, this.getDirect());
                break;
        }
        shots.add(shot);
        new Thread(shot).start();
    }
}
