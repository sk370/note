package top.sk370.completable_future;

import java.util.concurrent.CompletableFuture;

/**
 * 异常处理2
 * @author zhuyuqi
 * @version v0.0.1
 * @className Test07
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/10/04 15:57
 */
public class Test07 {
    private static Integer num = 10;
    public static void main(String[] args) throws Exception{
        System.out.println("主线程开始");
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("加 10 任务开始");
            num += 10;
            return num;
        }).handle((i,ex) ->{
            System.out.println("进入 handle 方法");
            if(ex != null){
                System.out.println("发生了异常,内容为:" + ex.getMessage());
                return -1;
            }else{
                System.out.println("正常完成,内容为: " + i);
                return i;
            }});
        System.out.println(future.get());
    }
}
