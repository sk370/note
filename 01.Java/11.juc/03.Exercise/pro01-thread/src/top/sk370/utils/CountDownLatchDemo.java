package top.sk370.utils;

import java.util.concurrent.CountDownLatch;

/**
 * @author zhuyuqi
 * @version v0.0.1
 * @className CountDownLatchDemo
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/10/03 21:48
 */
public class CountDownLatchDemo {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(6);//创建对象并设置初始值
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "号同学离开了教师");
                countDownLatch.countDown();//每次让计数器减一
            },String.valueOf(i)).start();
        }
        try {
            countDownLatch.await();//等待
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("都走光了，锁门！");
    }
}
