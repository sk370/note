package tankbattle;

/**
 * @desc: 代表子弹
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/10 14:47
 */
public class Shot implements Runnable{
    int x;
    int y;
    int direct = 0;
    int speed = 2;
    boolean isLive = true;

    public Shot(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    @Override
    public void run() {
        while (isLive){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            switch (direct){
                case 0:
                    y -= speed;
                    break;
                case 1:
                    x += speed;
                    break;
                case 2:
                    y += speed;
                    break;
                case 3:
                    x -= speed;
            }
//            System.out.println(x + " - " + y);
            if(!(x >= 0 && x <= 1000 && y >= 0 && y <= 750)){
                isLive = false;
                break;
            }
        }
    }
}
