package top.sk370.lock;

import sun.applet.Main;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhuyuqi
 * @version v0.0.1
 * @className CustomizedCommunication
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/10/03 15:24
 */
public class CustomizedCommunication {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                try {
                    shareResource.print5(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "AA").start();
        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                try {
                    shareResource.print10(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "BB").start();
        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                try {
                    shareResource.print15(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "CC").start();
    }
}
class ShareResource {
    private int flag = 1;
    private final Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void print5(int loop) throws InterruptedException {
        lock.lock();
        try {
            while (flag != 1){
                c1.await();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "：：" + i + "轮数：" + loop);
            }
            flag = 2;
            c2.signalAll();//通知指定线程
        }finally {
            lock.unlock();
        }
    }

    public void print10(int loop) throws InterruptedException {
        lock.lock();
        try {
            while (flag != 2){
                c2.await();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "：：" + i + "轮数：" + loop);
            }
            flag = 3;
            c3.signalAll();//通知指定线程
        }finally {
            lock.unlock();
        }
    }

    public void print15(int loop) throws InterruptedException {
        lock.lock();
        try {
            while (flag != 3){
                c3.await();
            }
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName() + "：：" + i + "轮数：" + loop);
            }
            flag = 1;
            c1.signalAll();//通知指定线程
        }finally {
            lock.unlock();
        }
    }
}