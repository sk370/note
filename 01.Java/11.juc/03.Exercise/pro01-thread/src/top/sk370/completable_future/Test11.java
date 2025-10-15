package top.sk370.completable_future;

import java.util.concurrent.CompletableFuture;

/**
 * 结果处理
 *
 * @author zhuyuqi
 * @version v0.0.1
 * @className Test11
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/10/15 19:47
 */
public class Test11 {
    public static void main(String[] args) throws Exception {
        System.out.println("主线程开始");
        //运行一个有返回值的异步任务
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("子线程开始任务");
                Thread.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "子线程完成了!";
        }).whenComplete((v, e) -> {
            if(e==null){
                System.out.println("子线程执行完没有发生异常。");
            }
        });
        //主线程阻塞
        String s = future.get();
        System.out.println("主线程结束, 子线程的结果为:" + s);
    }
}
