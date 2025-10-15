package top.sk370.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhuyuqi
 * @version v0.0.1
 * @className ThreadCommunication
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/10/03 14:17
 */
public class ThreadCommunication {
    public static void main(String[] args) {
        Share share = new Share();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "aa").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "bb").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "cc").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "dd").start();
    }
}

/**
 * 资源类
 */
class Share{
    private int number = 0;
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    /**
     * number等于0则加1，不等于0则等待
     * @throws InterruptedException
     */
    public void incr() throws InterruptedException {
        lock.lock();
        try {
            while (this.number != 0){
                condition.await();//线程等待获取jvm资源
            }
            number++;
            System.out.println(Thread.currentThread().getName() + "：" + number);
            condition.signalAll();//通知其他线程
        } finally {
            lock.unlock();
        }
    }

    /**
     * number不等于0则减1，等于0则等待
     * @throws InterruptedException
     */
    public void decr() throws InterruptedException {
        lock.lock();
        try {
            while (this.number ==0 ){
                condition.await();//线程等待获取jvm资源
            }
            number--;
            System.out.println(Thread.currentThread().getName() + "：" + number);
            condition.signalAll();//通知其他线程
        } finally {
            lock.unlock();
        }
    }
}