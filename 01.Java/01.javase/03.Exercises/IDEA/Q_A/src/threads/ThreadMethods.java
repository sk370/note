package threads;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/1 9:38
 */
public class ThreadMethods {
    public static void main(String[] args) throws InterruptedException {
        MyThread m = new MyThread();
        Thread t = new Thread(m);
        t.start();
        t.join();
        int j = m.i;
        System.out.println(j);
    }
}

class MyThread implements Runnable {
    int i;

    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        i = 100;
    }
}