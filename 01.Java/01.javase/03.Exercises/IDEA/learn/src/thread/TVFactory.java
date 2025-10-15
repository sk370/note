package thread;

import jdk.nashorn.internal.ir.IfNode;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/1 19:01
 */
public class TVFactory {
    public int capicaty;
    public int productNum;
    public int total;
    public int sold;

    public static void main(String[] args) {
        TVFactory tvFactory = new TVFactory();
        Productor productor = new Productor(tvFactory);
        Consumer consumer = new Consumer(tvFactory);
        productor.start();
        consumer.start();
    }

    public synchronized void product() {
        if(productNum < 10){
            productNum++;
            total++;
            System.out.println("正在生产第" + productNum + "件货");
            System.out.println("这是总共生产出的第" + total + "件货");
            if(productNum == 4){
                capicaty = productNum;
                productNum = 0;
                notify();
            }
        }else{
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public synchronized void sell() {
        if (capicaty > 0) {
            notify();
            System.out.println("正在出售第" + capicaty + "件货");
            capicaty--;
            sold++;
            System.out.println("这是总共卖出的第" + sold + "件货");
        }else {
            System.out.println("店里没货,卖不了");
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class Productor extends Thread {
    private TVFactory tvf;

    public Productor(TVFactory tvf) {
        this.tvf = tvf;
    }

    @Override
    public void run() {
        System.out.println("生产者开始生产");
        while(true){
            tvf.product();
            try {
                sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class Consumer extends Thread {
    private TVFactory tvf;

    public Consumer(TVFactory tvf) {
        this.tvf = tvf;
    }

    @Override
    public void run() {
        System.out.println("消费者开始消费");
        while (true) {
            tvf.sell();
            try {
                sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}