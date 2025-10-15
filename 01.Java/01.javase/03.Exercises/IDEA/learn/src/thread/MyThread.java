package thread;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/5/30 17:33
 */
public class MyThread extends Thread{
    private String WhoAmI;
    private int delay;
    @Override
    public void run() {
        try {
            getDelay();
            sleep(delay);
            System.out.println("当前执行的线程为：" + getName() + "，休眠时间为：" + delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public MyThread( String whoAmI) {
        super(whoAmI);
    }

    private int getDelay(){
        return delay = (int) (Math.random() * 1000);
    }
}
class TestThread{
    public static void main(String[] args) {
        MyThread myThread1 = new MyThread("线程1");
        MyThread myThread2 = new MyThread("线程2");
        MyThread myThread3 = new MyThread("线程3");
        myThread1.start();
        myThread2.start();
        myThread3.start();
    }
}