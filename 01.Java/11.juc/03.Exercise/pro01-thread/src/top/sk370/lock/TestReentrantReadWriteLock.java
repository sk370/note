package top.sk370.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author zhuyuqi
 * @version v0.0.1
 * @className TestReentrantReadWriteLock
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/10/03 13:00
 */
public class TestReentrantReadWriteLock {
    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    public static void main(String[] args) {
        final TestReentrantReadWriteLock test = new TestReentrantReadWriteLock();
        new Thread() {
            public void run() {
                test.get(Thread.currentThread());
            };
        }.start();
        new Thread() {
            public void run() {
                test.get(Thread.currentThread());
            };
        }.start();
    }

    public void get(Thread thread) {
        rwl.readLock().lock();
        try {
            long start = System.currentTimeMillis();
            while (System.currentTimeMillis() - start <= 1) {
                System.out.println(thread.getName() + "正在进行读操作");
            }
            System.out.println(thread.getName() + "读操作完毕");
        } finally {
            rwl.readLock().unlock();
        }
    }
}
