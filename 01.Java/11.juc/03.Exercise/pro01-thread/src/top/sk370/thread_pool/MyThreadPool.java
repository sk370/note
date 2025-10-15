package top.sk370.thread_pool;

import java.util.concurrent.*;

/**
 * @author zhuyuqi
 * @version v0.0.1
 * @className MyThreadPool
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/10/04 12:23
 */
public class MyThreadPool {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                5,
                2L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
    }
}
