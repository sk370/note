package top.sk370.completable_future;

import java.util.concurrent.CompletableFuture;

/**
 * 多结果合并2
 * @author zhuyuqi
 * @version v0.0.1
 * @className Test10_2
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/10/04 16:05
 */
public class Test10_2 {
    private static Integer num = 10;
    public static void main(String[] args) throws Exception{
        System.out.println("主线程开始");
        CompletableFuture<Integer>[] futures = new CompletableFuture[4];
        CompletableFuture<Integer> job1 = CompletableFuture.supplyAsync(() -> {
            try{
                Thread.sleep(5000);
                System.out.println("加 10 任务开始");num += 10;
                return num;
            }catch (Exception e){
                return 0;
            }
        });
        futures[0] = job1;
        CompletableFuture<Integer> job2 = CompletableFuture.supplyAsync(() -> {
            try{
                Thread.sleep(2000);
                System.out.println("乘以 10 任务开始");
                num = num * 10;
                return num;
            }catch (Exception e){
                return 1;
            }
        });
        futures[1] = job2;
        CompletableFuture<Integer> job3 = CompletableFuture.supplyAsync(() -> {
            try{
                Thread.sleep(3000);
                System.out.println("减以 10 任务开始");
                num = num * 10;
                return num;
            }catch (Exception e){
                return 2;
            }
        });
        futures[2] = job3;
        CompletableFuture<Integer> job4 = CompletableFuture.supplyAsync(() -> {
            try{
                Thread.sleep(4000);
                System.out.println("除以 10 任务开始");num = num * 10;
                return num;
            }catch (Exception e){
                return 3;
            }
        });
        futures[3] = job4;
        CompletableFuture<Object> future = CompletableFuture.anyOf(futures);
        System.out.println(future.get());
    }
}
