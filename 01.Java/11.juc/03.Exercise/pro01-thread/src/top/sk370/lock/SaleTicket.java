package top.sk370.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock操作多线程
 * @author zhuyuqi
 * @version v0.0.1
 * @className SaleTicket
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/10/03 11:52
 */
public class SaleTicket {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(()->{
            for (int i = 0; i < 30; i++) {
                ticket.sale();
            }
        }, "aa").start();

        new Thread(()->{
            for (int i = 0; i < 30; i++) {
                ticket.sale();
            }
        }, "bb").start();

        new Thread(()->{
            for (int i = 0; i < 30; i++) {
                ticket.sale();
            }
        }, "cc").start();
    }
}

class Ticket{
    private int number = 30;//30张票
    private final ReentrantLock lock = new ReentrantLock();//创建可重入锁

    /**
     * 售出
     */
    public void sale(){
        lock.lock();
        try {
            if(number > 0){
                System.out.println("当前票数：" + this.number + "，" + Thread.currentThread().getName() + "卖出了1张票，目前剩余" + --number + "张票");
            }
        } finally {
            lock.unlock();//确保不论有无异常，均释放锁
        }
    }
}