package tankbattle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/9 10:10
 * @desc: 创建画板
 */
public class MyPanel extends JPanel implements KeyListener, Runnable {
    HeroTank heroTank = null;
    Vector<EnemyTank> enemyTanks = new Vector<>();
    Vector<Node> nodes = new Vector<>();//用于保存读取到的坦克数据
    Vector<Bomb> bombs = new Vector<>();
    int enemyTankSize = 3;

    Image image1 = null;
    Image image2 = null;
    Image image3 = null;

    public MyPanel(String key) {
//        第一次开始游戏，游戏进度文件不存在会报异常，需要处理一下
        File file = new File(Recoder.getRecordFile());
        FileWriter fileWriter = null;
        if(!file.exists()){
            if(key.equals("2")){
                System.out.println("无游戏记录，只能开始新游戏");
                key = "1";
            }
            try {
                file.createNewFile();
                fileWriter = new FileWriter(file,true);
                fileWriter.write("0");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }finally {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }


//        获取Recoder类文件中记录的坦克对象，读取上次保存的游戏数据
        nodes = Recoder.getNodes();
//        获取场上存活坦克，传递给文件记录类
        Recoder.setEnemyTanks(enemyTanks);
//        初始化爆炸效果的图片
        image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.gif"));
        image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.gif"));
        image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.gif"));

//        创建己方坦克
        heroTank = new HeroTank(100, 100, 2);

//        游戏类型判断：1.新游戏，2.上局游戏
        switch (key){
            case "1":
                //        创建敌方坦克
                for (int i = 0; i < enemyTankSize; i++) {
                    EnemyTank enemyTank = new EnemyTank(100 * (i + 1), 300, 0);
                //        创建敌方坦克子弹
                    Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY(), enemyTank.getDirect());
                    enemyTank.shots.add(shot);
                    new Thread(shot).start();
                    new Thread(enemyTank).start();
                    enemyTanks.add(enemyTank);
                //        将创建的所有EnemyTank对象提供给EnemyTank类，进行判断是否重叠
                //        由于setEnemyTanks是定义在EnemyTank的类中，所以就必须由EnemyTank的对象去调用
                    enemyTank.setEnemyTanks(enemyTanks);
                }
                break;
            case "2":
                //        创建敌方坦克
                for (int i = 0; i < nodes.size(); i++) {
                    Node node = nodes.get(i);
                    EnemyTank enemyTank = new EnemyTank(node.getX(), node.getY(), node.getD());
                    //        创建敌方坦克子弹
                    Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY(), enemyTank.getDirect());
                    enemyTank.shots.add(shot);
                    new Thread(shot).start();
                    new Thread(enemyTank).start();
                    enemyTanks.add(enemyTank);
                    //        将创建的所有EnemyTank对象提供给EnemyTank类，进行判断是否重叠
                    //        由于setEnemyTanks是定义在EnemyTank的类中，所以就必须由EnemyTank的对象去调用
                    enemyTank.setEnemyTanks(enemyTanks);
                }
                break;
            default:
                System.out.println("输入有误");
                break;
        }

//      播放游戏背景音乐
        new AePlayWave("D:\\IDEA\\practise\\src\\tankbattle\\111.wav").start();
}

    /**
     * @desc 显示游戏记录
     * @param g
     */
    public void showInfo(Graphics g){
        g.setColor(Color.BLACK);
        Font font = new Font("宋体", Font.BOLD, 25);
        g.setFont(font);
        g.drawString("累计击毁敌方坦克", 1020, 30);
        drawTank(1020, 60, g, 0, 1);
//        绘制坦克时画笔颜色改变过，所以需要重新设置颜色
        g.setColor(Color.BLACK);
        g.drawString(Recoder.getAllEnemyTankNum() + "", 1080, 100);
    }

    /**
     * @param s
     * @param tank
     * @desc 根据子弹与坦克的坐标位置是否重叠判断是否击中
     */

    public  void hitTanks(Shot s, Tank tank) {
//        是否击中敌方坦克
        if(tank instanceof EnemyTank){
            EnemyTank enemyTank = (EnemyTank)tank;
            switch (enemyTank.getDirect()) {
                case 0:
                case 2:
                    if (s.x > enemyTank.getX() && s.x < enemyTank.getX() + 40 && s.y > enemyTank.getY() && s.y < enemyTank.getY() + 60) {
                        s.isLive = false;
                        enemyTank.isLive = false;
                        enemyTanks.remove(enemyTank);
//                        将击毁坦克数量添加到Recoder中
                        Recoder.addEnemyTankNum();

//                    击中后显示炸弹效果
                        Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                        bombs.add(bomb);
                    }
                    break;
                case 1:
                case 3:
                    if (s.x > enemyTank.getX() && s.x < enemyTank.getX() + 60 && s.y > enemyTank.getY() && s.y < enemyTank.getY() + 40) {
                        s.isLive = false;
                        enemyTank.isLive = false;
                        enemyTanks.remove(enemyTank);
//                        将击毁坦克数量添加到Recoder中
                        Recoder.addEnemyTankNum();

//                    击中后显示炸弹效果
                        Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                        bombs.add(bomb);
                    }
                    break;
            }
        }
//        是否击中己方坦克
        if(tank instanceof HeroTank){
            HeroTank heroTank = (HeroTank)tank;
            switch (heroTank.getDirect()) {
                case 0:
                case 2:
                    if (s.x > heroTank.getX() && s.x < heroTank.getX() + 40 && s.y > heroTank.getY() && s.y < heroTank.getY() + 60) {
                        s.isLive = false;
                        heroTank.isLive = false;
//                    击中后显示炸弹效果
                        Bomb bomb = new Bomb(heroTank.getX(), heroTank.getY());
                        bombs.add(bomb);
                    }
                    break;
                case 1:
                case 3:
                    if (s.x > heroTank.getX() && s.x < heroTank.getX() + 60 && s.y > heroTank.getY() && s.y < heroTank.getY() + 40) {
                        s.isLive = false;
                        heroTank.isLive = false;
//                    击中后显示炸弹效果
                        Bomb bomb = new Bomb(heroTank.getX(), heroTank.getY());
                        bombs.add(bomb);
                    }
                    break;
            }
        }

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
//        将游戏区填充满整个绘图区，且游戏背景为黑色(默认）
//        g.setColor(Color.white);//设置游戏区为白色
        g.fillRect(0, 0, 1000, 750);

//        显示游戏记录
        showInfo(g);

//        己方坦克绘制 - 封装方法
        if(heroTank.isLive){
            drawTank(heroTank.getX(), heroTank.getY(), g, heroTank.getDirect(), 0);
        }
//        绘制己方子弹
        for (int j = 0; j < heroTank.shots.size(); j++) {
            Shot shot = heroTank.shots.get(j);
            if (shot != null && shot.isLive) {
                g.setColor(Color.cyan);
                g.draw3DRect(shot.x, shot.y, 1, 1, false);
            }else {
                heroTank.shots.remove(shot);
            }
        }

//        敌方坦克绘制 - 封装方法
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            if (enemyTank.isLive) {
                drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 1);
//            绘制敌方子弹
                for (int j = 0; j < enemyTank.shots.size(); j++) {
                    Shot shot = enemyTank.shots.get(j);
                    if (shot.isLive) {
                        g.draw3DRect(shot.x, shot.y, 1, 1, false);
                    } else {
                        enemyTank.shots.remove(shot);
                    }
                }
            }
        }

//        绘制爆炸效果
        if(bombs.size() != 0){
            for (int i = 0; i < bombs.size(); i++) {
                Bomb bomb = bombs.get(i);
                if(bomb.life > 6){
                    g.drawImage(image1, bomb.x, bomb.y, 60, 60, this);
                } else if (bomb.life > 3) {
                    g.drawImage(image2, bomb.x, bomb.y, 60, 60, this);
                }else {
                    g.drawImage(image3, bomb.x, bomb.y, 60, 60, this);
                }
                bomb.lifeDown();
                if(bomb.isLive == false){
                    bombs.remove(bomb);
                }
            }
        }
    }

    /**
     * @param x      坦克左上角坐标
     * @param y      坦克左上角坐标
     * @param g      画笔
     * @param direct 坦克炮筒方向：0 上、1 右、2 下、 3 左
     * @param type   坦克类型:0 己方坦克、1 敌方坦克
     * @desc 绘制坦克
     */
    public void drawTank(int x, int y, Graphics g, int direct, int type) {
//      根据坦克类型设置颜色
        switch (type) {
            case 0:
                g.setColor(Color.cyan);
                break;
            case 1:
                g.setColor(Color.yellow);
                break;
        }
//        根据炮筒方向，绘制坦克
        switch (direct) {
            case 0:
                g.fill3DRect(x, y, 10, 60, false);//坦克左侧矩形
                g.fill3DRect(x + 30, y, 10, 60, false);//坦克右侧矩形
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//坦克中间矩形
                g.fillOval(x + 10, y + 20, 20, 20);//坦克中间圆形
                g.drawLine(x + 20, y, x + 20, y + 30);//炮筒
                break;
            case 1:
                g.fill3DRect(x, y, 60, 10, false);//坦克上侧矩形
                g.fill3DRect(x, y + 30, 60, 10, false);//坦克下侧矩形
                g.fill3DRect(x + 10, y + 10, 40, 20, false);//坦克中间矩形
                g.fillOval(x + 20, y + 10, 20, 20);//坦克中间圆形
                g.drawLine(x + 30, y + 20, x + 60, y + 20);//炮筒
                break;
            case 2:
                g.fill3DRect(x, y, 10, 60, false);//坦克左侧矩形
                g.fill3DRect(x + 30, y, 10, 60, false);//坦克右侧矩形
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//坦克中间矩形
                g.fillOval(x + 10, y + 20, 20, 20);//坦克中间圆形
                g.drawLine(x + 20, y + 30, x + 20, y + 60);//炮筒
                break;
            case 3:
                g.fill3DRect(x, y, 60, 10, false);//坦克上侧矩形
                g.fill3DRect(x, y + 30, 60, 10, false);//坦克下侧矩形
                g.fill3DRect(x + 10, y + 10, 40, 20, false);//坦克中间矩形
                g.fillOval(x + 20, y + 10, 20, 20);//坦克中间圆形
                g.drawLine(x, y + 20, x + 30, y + 20);//炮筒
                break;
            default:
                System.out.println("暂时未处理");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * @param e
     * @desc 按键控制坦克方向:w 上、
     */
    @Override
    public void keyPressed(KeyEvent e) {
//        控制移动方向
        if (e.getKeyCode() == KeyEvent.VK_W) //按下w键
        {
            heroTank.setDirect(0);
            if(heroTank.getY() > 0){
                heroTank.moveUp();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_A) {//按下A键
            heroTank.setDirect(3);
            if(heroTank.getX() > 0){
                heroTank.moveLeft();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_S) {//按下S键
            heroTank.setDirect(2);
            if(heroTank.getY() + 60 < 750){
                heroTank.moveDown();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_D) {//按下D键
            heroTank.setDirect(1);
            if(heroTank.getX() + 60 < 1000){
                heroTank.moveRight();
            }
        }
//        控制发射子弹
        if (e.getKeyCode() == KeyEvent.VK_J) {
            heroTank.shotEnemyTank();
        }
//        方向改变后更新绘图区
        this.repaint();//后面启动线程的run()方法有重绘功能，但这里打开的话，相当于不等画板更新，就改变了方向
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
//            判断是否击中敌方坦克
            for (int j = 0; j < heroTank.shots.size(); j++) {
                Shot shot = heroTank.shots.get(j);
                if (shot != null && shot.isLive) {
                    for (int i = 0; i < enemyTanks.size(); i++) {
                        EnemyTank enemyTank = enemyTanks.get(i);
                        hitTanks(shot, enemyTank);
                    }
                }
            }
//            判断是否击中我方坦克
            if(heroTank.isLive){
                for (int k = 0; k < enemyTanks.size(); k++) {
                    EnemyTank enemyTank = enemyTanks.get(k);
                    for (int j = 0; j < enemyTank.shots.size(); j++) {
                        Shot shot = enemyTank.shots.get(j);
                        if (shot != null && shot.isLive) {
                            hitTanks(shot, heroTank);
                        }
                    }
                }
            }
            this.repaint();
        }
    }
}
