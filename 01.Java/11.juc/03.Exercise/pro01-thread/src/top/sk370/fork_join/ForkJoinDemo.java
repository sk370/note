package top.sk370.fork_join;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * 计算 1+2+3.........+1000,==每 100 个数切分一个子任务
 * 二分法拆分
 * @author zhuyuqi
 * @version v0.0.1
 * @className ForkJoinDemo
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/10/04 14:49
 */
public class ForkJoinDemo{
    public static void main(String[] args) {
        MyTask myTask = new MyTask(0, 1000);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(myTask);
        try {
            Integer res = forkJoinTask.get();
            System.out.println(res);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }finally {
            forkJoinPool.shutdown();
        }

    }
}
class MyTask extends RecursiveTask<Integer> {

    private final static Integer VALUE = 10;//拆分差值不能超过10
    private int begin;
    private int end;
    private int result;

    public MyTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if(end - begin <= VALUE){
            for (int i = begin; i <= end; i++) {
                result += i;
            }
        }else {
            int middle = (begin + end)/2;
            MyTask taskExample1 = new MyTask(begin, middle);//拆分左边
            MyTask taskExample2 = new MyTask(middle + 1, end);//拆分右边
            taskExample1.fork();
            taskExample2.fork();
            result = taskExample1.join() + taskExample2.join();//合并结果
        }
        return result;
    }
}
