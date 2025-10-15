package top.sk370.multi_thread_lock;

import sun.applet.Main;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author zhuyuqi
 * @version v0.0.1
 * @className WriteLockDown
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/10/04 10:09
 */
public class WriteLockDown {
    public static void main(String[] args) {
        ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();//可重入读写锁
        ReentrantReadWriteLock.ReadLock readLock = rwLock.readLock();//读锁
        ReentrantReadWriteLock.WriteLock writeLock = rwLock.writeLock();//写锁

        writeLock.lock();//获取写锁
        System.out.println(1);
        readLock.lock();//获取读锁
        System.out.println(2);
        writeLock.unlock();//释放写锁
        readLock.unlock();//释放读锁
    }
}
