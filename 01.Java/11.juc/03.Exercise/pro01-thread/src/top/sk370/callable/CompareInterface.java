package top.sk370.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author zhuyuqi
 * @version v0.0.1
 * @className CompareInterface
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/10/03 21:13
 */
public class CompareInterface {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(()->{
            return 200;
        });
        new Thread(futureTask, "lucy").start();
        System.out.println(futureTask.get());
    }
}
class MyThread1 implements Runnable{
    @Override
    public void run() {

    }
}
class MyThread2 implements Callable{
    @Override
    public Integer call() throws Exception {
        return 200;
    }
}