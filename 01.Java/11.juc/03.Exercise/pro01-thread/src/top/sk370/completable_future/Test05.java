package top.sk370.completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

/**
 * 消费处理结果：
 * @author zhuyuqi
 * @version v0.0.1
 * @className Test05
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/10/04 15:49
 */
public class Test05 {
    private static Integer num = 10;
    public static void main(String[] args) throws Exception{
        System.out.println("主线程开始");
        CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("加 10 任务开始");
                num += 10;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return num;
        }).thenApply(integer -> {
            return num * num;
        }).thenAccept(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println("子线程全部处理完成,最后调用了 accept,结果为:" + integer);
            }
        });
    }
}
