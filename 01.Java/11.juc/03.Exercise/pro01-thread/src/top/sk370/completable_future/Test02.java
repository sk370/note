package top.sk370.completable_future;

import java.util.concurrent.CompletableFuture;

/**
 * 没有返回值的异步任务
 * @author zhuyuqi
 * @version v0.0.1
 * @className Test02
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/10/04 15:44
 */
public class Test02 {
    public static void main(String[] args) throws Exception{
        System.out.println("主线程开始");
        //运行一个没有返回值的异步任务
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
        try {
            System.out.println("子线程启动干活");
            Thread.sleep(5000);
            System.out.println("子线程完成");
        } catch (Exception e) {
            e.printStackTrace();
        }
    });
        //主线程阻塞
        future.get();
        System.out.println("主线程结束");
    }
}
