package top.sk370.completable_future;

import java.util.concurrent.CompletableFuture;

/**
 * 主线程里面创建一个CompletableFuture，然后主线程调用get方法阻塞，最后在一个子线程中使其终止
 * @author zhuyuqi
 * @version v0.0.1
 * @className Test01
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/10/04 15:42
 */
public class Test01 {
    public static void main(String[] args) throws Exception{
        CompletableFuture<String> future = new CompletableFuture<>();
        new Thread(() -> {
            try{
                System.out.println(Thread.currentThread().getName() + "子线程开始干活");
                //子线程睡 5 秒
                Thread.sleep(5000);
                //在子线程中完成主线程
                future.complete("success");
            }catch (Exception e){
                e.printStackTrace();
            }
        }, "A").start();
        //主线程调用get方法阻塞
        System.out.println("主线程调用 get 方法获取结果为: " + future.get());
        System.out.println("主线程完成,阻塞结束!!!!!!");
    }
}
