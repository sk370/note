package top.sk370.utils;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 6辆汽车抢3个车位
 * @author zhuyuqi
 * @version v0.0.1
 * @className SemaphoreDemo
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/10/03 22:08
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();//抢占
                    System.out.println(Thread.currentThread().getName() + "抢到了车位");
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));//设置停车时间，模拟线程占用
                    System.out.println(Thread.currentThread().getName() + "离开了车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();//释放车位
                }
            },String.valueOf(i)).start();
        }

    }
}
