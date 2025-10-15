package top.sk370.completable_future;

import java.util.concurrent.CompletableFuture;

/**
 * 异常处理1
 * @author zhuyuqi
 * @version v0.0.1
 * @className Test06
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/10/04 15:54
 */
public class Test06 {
    private static Integer num = 10;
    public static void main(String[] args) throws Exception{
        System.out.println("主线程开始");
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            int i= 1/0;
            System.out.println("加 10 任务开始");
            num += 10;
            return num;
        }).exceptionally(ex -> {
            System.out.println(ex.getMessage());
            return -1;
        });
        System.out.println(future.get());
    }
}
