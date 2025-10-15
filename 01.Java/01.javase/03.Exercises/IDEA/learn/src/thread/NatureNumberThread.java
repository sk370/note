package thread;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/5/30 11:04
 */
public class NatureNumberThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        NatureNumberThread natureNumberThread = new NatureNumberThread();
        natureNumberThread.start();
    }
}
