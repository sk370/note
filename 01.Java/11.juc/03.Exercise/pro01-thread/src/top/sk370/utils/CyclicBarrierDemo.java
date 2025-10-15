package top.sk370.utils;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author zhuyuqi
 * @version v0.0.1
 * @className CyclicBarrierDemo
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/10/03 21:56
 */
public class CyclicBarrierDemo {
    private static final int NUMBER = 7;
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(NUMBER, ()->{
            System.out.println("集齐七颗许愿");
        });
        for (int i = 0; i < 9; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "星龙珠收集到了");
                try {
                    cyclicBarrier.await();//等待
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
