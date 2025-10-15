package tankbattle;

import java.util.Vector;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/9 14:51
 */
public class EnemyTank extends Tank implements Runnable{
    Vector<Shot> shots = new Vector<>();
    Vector<EnemyTank> enemyTanks = new Vector<>();
    boolean isLive = true;
    public EnemyTank(int x, int y, int direct) {
        super(x, y, direct);
    }
    public void setEnemyTanks(Vector<EnemyTank> enemyTanks){
        this.enemyTanks = enemyTanks;
    }

    /**
     * @desc 判断当前坦克是否与其他坦克存在重叠。这里由于坦克是矩形，所以转向的时候仍然存在重叠情况，目前未处理。
     * @return
     */
    public boolean isTouchEnemyTank(){
        switch (this.getDirect()){
            case 0:
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
//                    和this坦克之外的所有坦克进行比较
                    if(this != enemyTank){
                        if(enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2){
//                            当前坦克左上角是否进入敌方坦克内部
                            if(this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60){
                                return true;
                            }
//                            当前坦克右上角是否进入敌方坦克内部
                            if(this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60){
                                return true;
                            }
                        }

                        if(enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3){
//                            当前坦克左上角是否进入敌方坦克内部
                            if(this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40){
                                return true;
                            }
//                            当前坦克右上角是否进入敌方坦克内部
                            if(this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40){
                                return true;
                            }
                        }
                    }
                }
                break;
            case 1:
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
//                    和this坦克之外的所有坦克进行比较
                    if(this != enemyTank){
                        if(enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2){
//                            当前坦克右下角是否进入敌方坦克内部
                            if(this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 40
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40<= enemyTank.getY() + 60){
                                return true;
                            }
//                            当前坦克右上角是否进入敌方坦克内部
                            if(this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60){
                                return true;
                            }
                        }

                        if(enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3){
//                            当前坦克左上角是否进入敌方坦克内部
                            if(this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40){
                                return true;
                            }
//                            当前坦克右上角是否进入敌方坦克内部
                            if(this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40){
                                return true;
                            }
                        }
                    }
                }
                break;
            case 2:
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
//                    和this坦克之外的所有坦克进行比较
                    if(this != enemyTank){
                        if(enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2){
//                            当前坦克左下角是否进入敌方坦克内部
                            if(this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 60){
                                return true;
                            }
//                            当前坦克右下角是否进入敌方坦克内部
                            if(this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 40
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 60){
                                return true;
                            }
                        }

                        if(enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3){
//                            当前坦克左下角是否进入敌方坦克内部
                            if(this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 40){
                                return true;
                            }
//                            当前坦克右下角是否进入敌方坦克内部
                            if(this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 60
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 40){
                                return true;
                            }
                        }
                    }
                }
                break;
            case 3:
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
//                    和this坦克之外的所有坦克进行比较
                    if(this != enemyTank){
                        if(enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2){
//                            当前坦克左上角是否进入敌方坦克内部
                            if(this.getX()  >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60){
                                return true;
                            }
//                            当前坦克左下角是否进入敌方坦克内部
                            if(this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 60){
                                return true;
                            }
                        }

                        if(enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3){
//                            当前坦克左上角是否进入敌方坦克内部
                            if(this.getX()  >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40){
                                return true;
                            }
//                            当前坦克左下角是否进入敌方坦克内部
                            if(this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 40){
                                return true;
                            }
                        }
                    }
                }
                break;
        }
        return false;
    }

    @Override
    public void run() {
        while (true){
//            敌方坦克发子弹
            if(isLive && shots.size() < 3){
                Shot shot = null;
                switch (getDirect()){
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
//            敌方坦克移动
            switch (getDirect()){
                case 0:
                    for (int i = 0; i < 30; i++) {
                        if(getY() > 0 && !isTouchEnemyTank()){
                            moveUp();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 1:
                    for (int i = 0; i < 30; i++) {
                        if(getX() + 60 < 1000 && !isTouchEnemyTank()){
                            moveRight();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i < 30; i++) {
                        if(getY() + 60 < 750 && !isTouchEnemyTank()){
                            moveDown();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i < 30; i++) {
                        if(getX() > 0 && !isTouchEnemyTank()){
                            moveLeft();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
            }
//          坦克随机改变方向
            setDirect((int)(Math.random() * 4));
            if(isLive == false){
                break;
            }
        }
    }
}
