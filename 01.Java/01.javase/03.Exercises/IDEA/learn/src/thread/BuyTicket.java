package thread;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/1 16:36
 */
public class BuyTicket {
    public static void main(String[] args) {
        Conductor conductor = new Conductor();
        XDJ xdj = new XDJ(conductor);
        ZS zs = new ZS(conductor);
        WDN wdn = new WDN(conductor);
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

class WDN extends Thread {
    private int money = 20;
    private Conductor c;

    public WDN(Conductor c) {
        this.c = c;
    }

    @Override
    public void run() {
        c.sold(money);
    }
}

class XDJ extends Thread {
    private int money = 10;
    private Conductor c;

    public XDJ(Conductor c) {
        this.c = c;
    }

    @Override
    public void run() {
        c.sold(money);
    }
}

class ZS extends Thread {
    private int money = 5;
    private Conductor c;

    public ZS(Conductor c) {
        this.c = c;
    }

    @Override
    public void run() {
        c.sold(money);
    }
}

class Conductor {
    public int balance = 5;
    private int ticket = 5;

    public synchronized void sold(int money) {

        while(true){
                System.out.println(Thread.currentThread().getName() + "正在买票");
                if (balance < money - ticket) {
                    System.out.println("但售票员余额不足");
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }else {
                    System.out.println(Thread.currentThread().getName() + "卖到了1张票");
                    balance += 5;
                    this.notifyAll();
                    break;
                }
        }


    }
}