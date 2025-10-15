package top.sk370.completable_future;

import java.util.concurrent.CompletableFuture;

/**
 * 结果合并1
 * @author zhuyuqi
 * @version v0.0.1
 * @className Test08
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/10/04 15:59
 */
public class Test08 {
    private static Integer num = 10;
    public static void main(String[] args) throws Exception{
        System.out.println("主线程开始");
        //第一步加 10
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("加 10 任务开始");
            num += 10;
            return num;
        });
        //合并
        CompletableFuture<Integer> future1 = future.thenCompose(i ->
                //再来一个 CompletableFuture
                CompletableFuture.supplyAsync(() -> {
                    return i + 1;
                }));
        System.out.println(future.get());
        System.out.println(future1.get());
    }
}
