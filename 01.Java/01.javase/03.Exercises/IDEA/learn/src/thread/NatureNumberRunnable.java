package thread;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/5/30 11:16
 */
public class NatureNumberRunnable implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            System.out.println(i);
        }
    }
    public static void main(String[] args) {
        NatureNumberRunnable natureNumberRunnable = new NatureNumberRunnable();
        Thread thread = new Thread(natureNumberRunnable);
        thread.start();

    }
}
