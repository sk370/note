package top.sk370.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author zhuyuqi
 * @version v0.0.1
 * @className BlockingQueueDemo
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/10/04 10:51
 */
public class BlockingQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.element());
    }
}
