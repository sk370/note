package top.sk370.completable_future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;

/**
 * 结果合并2
 * @author zhuyuqi
 * @version v0.0.1
 * @className Test09
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/10/04 16:01
 */
public class Test09 {
    private static Integer num = 10;
    public static void main(String[] args) throws Exception{
        System.out.println("主线程开始");
        CompletableFuture<Integer> job1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("加 10 任务开始");
            num += 10;
            return num;
        });
        CompletableFuture<Integer> job2 = CompletableFuture.supplyAsync(() -> {System.out.println("乘以 10 任务开始");
            num = num * 10;
            return num;
        });
        //合并两个结果
        CompletableFuture<Object> future = job1.thenCombine(job2, new
                BiFunction<Integer, Integer, List<Integer>>() {
                    @Override
                    public List<Integer> apply(Integer a, Integer b) {
                        List<Integer> list = new ArrayList<>();
                        list.add(a);
                        list.add(b);
                        return list;
                    }
                });
        System.out.println("合并结果为:" + future.get());
    }
}
