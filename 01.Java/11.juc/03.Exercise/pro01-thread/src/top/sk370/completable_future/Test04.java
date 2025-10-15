package top.sk370.completable_future;

import java.util.concurrent.CompletableFuture;

/**
 * 依赖线程串行化：先对一个数加 10,然后取平方
 * @author zhuyuqi
 * @version v0.0.1
 * @className Test04
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/10/04 15:48
 */
public class Test04 {
    private static Integer num = 10;
    public static void main(String[] args) throws Exception {
        System.out.println("主线程开始");
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
                    try {
                        System.out.println("加 10 任务开始");
                        num += 10;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return num;
                }).thenApply(integer -> {
                    return num * num;
                });
        Integer integer = future.get();
        System.out.println("主线程结束, 子线程的结果为:" + integer);
    }
}
