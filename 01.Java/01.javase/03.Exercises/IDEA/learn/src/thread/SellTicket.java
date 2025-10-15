package thread;


/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/1 15:40
 */
public class SellTicket {
    public static void main(String[] args) {
        Window window = new Window();
        Thread thread1 = new Thread(window);
        Thread thread2 = new Thread(window);
        Thread thread3 = new Thread(window);
        thread1.start();
        thread2.start();
        thread3.start();
    }
}

class Window implements Runnable {
    private int ticket = 0;

    @Override
    public void run() {
        while (ticket < 100) {
            sell();
        }
    }

    private synchronized void sell() {
        notify();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ticket++;
        System.out.println(Thread.currentThread().getName() + "正在售出第" + ticket + "张票");
        if (ticket == 100) {
            return;
        }
        try {
            wait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}