package thread;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/5/30 14:14
 */
public class SingletonLazyLoad2 {
    private static SingletonLazyLoad2 singletonLazyLoad2 = null;

    private SingletonLazyLoad2() {
    }

    //    public static synchronized SingletonLazyLoad2 getSingletonLazyLoad2() {
    public static  SingletonLazyLoad2 getSingletonLazyLoad2() {
        if (singletonLazyLoad2 == null) {
            synchronized (SingletonLazyLoad2.class) {
                if (singletonLazyLoad2 == null) {
                    singletonLazyLoad2 = new SingletonLazyLoad2();
                }
            }
        }
        return singletonLazyLoad2;
    }
}

class SingletonThread2 implements Runnable {
    @Override
    public void run() {
        SingletonLazyLoad2 s = SingletonLazyLoad2.getSingletonLazyLoad2();
        System.out.println(s);
    }
}

class SingletonTest2 {
    public static void main(String[] args) {
        SingletonThread2 singletonThread2 = new SingletonThread2();
        Thread thread1 = new Thread(singletonThread2);
        Thread thread2 = new Thread(singletonThread2);
        thread1.start();
        thread2.start();
    }
}