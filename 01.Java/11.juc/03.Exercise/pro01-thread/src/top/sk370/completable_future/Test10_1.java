package top.sk370.completable_future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * 合并多个结果1
 * @author zhuyuqi
 * @version v0.0.1
 * @className Test10_1
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/10/04 16:03
 */
public class Test10_1 {
    private static Integer num = 10;
    public static void main(String[] args) throws Exception{
        System.out.println("主线程开始");
        List<CompletableFuture> list = new ArrayList<>();
        CompletableFuture<Integer> job1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("加 10 任务开始");
            num += 10;
            return num;
        });
        list.add(job1);
        CompletableFuture<Integer> job2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("乘以 10 任务开始");num = num * 10;
            return num;
        });
        list.add(job2);
        CompletableFuture<Integer> job3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("减以 10 任务开始");
            num = num * 10;
            return num;
        });
        list.add(job3);
        CompletableFuture<Integer> job4 = CompletableFuture.supplyAsync(() -> {
            System.out.println("除以 10 任务开始");
            num = num * 10;
            return num;
        });
        list.add(job4);
        //多任务合并
        List<Integer> collect =
                list.stream().map(CompletableFuture<Integer>::join).collect(Collectors.toList());
        System.out.println(collect);
    }
}
