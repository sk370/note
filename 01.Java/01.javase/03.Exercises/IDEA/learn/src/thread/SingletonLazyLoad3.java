package thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/5/30 14:14
 */
public class SingletonLazyLoad3 {
    private static SingletonLazyLoad3 singletonLazyLoad3 = null;
    private static ReentrantLock lock = new ReentrantLock();

    private SingletonLazyLoad3() {
    }

    public static SingletonLazyLoad3 getSingletonLazyLoad3() {
        lock.lock();
        if (singletonLazyLoad3 == null) {
            singletonLazyLoad3 = new SingletonLazyLoad3();
        }
        lock.unlock();
        return singletonLazyLoad3;
    }
}

class SingletonThread3 implements Runnable {
    @Override
    public void run() {
        SingletonLazyLoad3 s = SingletonLazyLoad3.getSingletonLazyLoad3();
        System.out.println(s);
    }
}

class SingletonTest3 {
    public static void main(String[] args) {
        SingletonThread3 singletonThread3 = new SingletonThread3();
        Thread thread1 = new Thread(singletonThread3);
        Thread thread2 = new Thread(singletonThread3);
        thread1.start();
        thread2.start();
    }
}