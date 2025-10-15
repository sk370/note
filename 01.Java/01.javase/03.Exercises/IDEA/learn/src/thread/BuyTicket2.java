package thread;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/1 16:36
 */
public class BuyTicket2 {
    public static void main(String[] args) {
        Conductor2 conductor = new Conductor2();
        XDJ2 xdj = new XDJ2(conductor);
        ZS2 zs = new ZS2(conductor);
        WDN2 wdn = new WDN2(conductor);
        Thread thread1 = new Thread(xdj);
        Thread thread2 = new Thread(zs);
        Thread thread3 = new Thread(wdn);
        thread1.setName("谢大脚");
        thread2.setName("赵四");
        thread3.setName("王大拿");
        thread3.start();
        thread1.start();
        thread2.start();
    }
}

class WDN2 extends Thread {
    private int money = 20;
    private Conductor2 c;

    public WDN2(Conductor2 c) {
        this.c = c;
    }

    @Override
    public void run() {
        c.sold(money);
    }
}

class XDJ2 extends Thread {
    private int money = 10;
    private Conductor2 c;

    public XDJ2(Conductor2 c) {
        this.c = c;
    }

    @Override
    public void run() {
        c.sold(money);
    }
}

class ZS2 extends Thread {
    private int money = 5;
    private Conductor2 c;

    public ZS2(Conductor2 c) {
        this.c = c;
    }

    @Override
    public void run() {
        c.sold(money);
    }
}

class Conductor2 {
    public int balance = 5;
    private int ticket = 5;

    public void sold(int money) {

        while (true) {
            synchronized (Conductor.class) {
                System.out.println(Thread.currentThread().getName() + "正在买票");
                if (balance < money - ticket) {
                    System.out.println("但售票员余额不足");
                } else {
                    System.out.println(Thread.currentThread().getName() + "卖到了1张票");
                    balance += 5;
                    break;
                }
            }
        }
    }
}