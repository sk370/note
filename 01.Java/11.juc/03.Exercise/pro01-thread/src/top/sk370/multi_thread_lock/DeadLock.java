package top.sk370.multi_thread_lock;

/**
 * @author zhuyuqi
 * @version v0.0.1
 * @className DeadLock
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/10/03 18:23
 */
public class DeadLock {
    static Object a = new Object();
    static Object b = new Object();
    public static void main(String[] args) {
        new Thread(()->{
            synchronized (a){
                System.out.println(Thread.currentThread().getName() + "持有锁a，试图获取锁b");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (b){
                    System.out.println(Thread.currentThread().getName() + "获取锁b");
                }
            }
        },"A").start();

        new Thread(()->{
            synchronized (b){
                System.out.println(Thread.currentThread().getName() + "持有锁b，试图获取锁a");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (a){
                    System.out.println(Thread.currentThread().getName() + "获取锁a");
                }
            }
        },"B").start();
    }
}
