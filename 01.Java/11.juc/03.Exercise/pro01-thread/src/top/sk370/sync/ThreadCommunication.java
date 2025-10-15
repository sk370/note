package top.sk370.sync;

import sun.security.provider.SHA;

/**
 * @author zhuyuqi
 * @version v0.0.1
 * @className ThreadCommunication
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/10/03 14:17
 */
public class ThreadCommunication {
    public static void main(String[] args) {
        Share share = new Share();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "aa").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "bb").start();
//        new Thread(()->{
//            for (int i = 0; i < 10; i++) {
//                try {
//                    share.incr();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, "cc").start();
//        new Thread(()->{
//            for (int i = 0; i < 10; i++) {
//                try {
//                    share.decr();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, "dd").start();
    }
}

/**
 * 资源类
 */
class Share{
    private int number = 0;

    /**
     * number等于0则加1，不等于0则等待
     * @throws InterruptedException
     */
    public synchronized void incr() throws InterruptedException {
        if(this.number != 0){
            this.wait();//线程等待获取jvm资源
        }
        number++;
        System.out.println(Thread.currentThread().getName() + "：" + number);
        this.notify();//通知其他线程
    }

    /**
     * number不等于0则减1，等于0则等待
     * @throws InterruptedException
     */
    public synchronized void decr() throws InterruptedException {
        if(this.number !=1){
            this.wait();//线程等待获取jvm资源
        }
        number--;
        System.out.println(Thread.currentThread().getName() + "：" + number);
        this.notify();//通知其他线程
    }
}