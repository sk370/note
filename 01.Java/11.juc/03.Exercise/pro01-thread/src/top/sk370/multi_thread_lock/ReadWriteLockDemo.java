package top.sk370.multi_thread_lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author zhuyuqi
 * @version v0.0.1
 * @className ReadWriteLock
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/10/03 22:30
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 0; i < 5; i++) {
            final int num = i;
            new Thread(()->{
                myCache.put(num+"",num+"");
            },String.valueOf(i)).start();
        }

        for (int i = 0; i < 5; i++) {
            final int num = i;
            new Thread(()->{
                myCache.get(num+"");
            },String.valueOf(i)).start();
        }
    }

}
class  MyCache{
    private volatile Map<String, Object> map = new HashMap<>();
    private ReadWriteLock rwLock = new ReentrantReadWriteLock();
    public void put(String key, Object value){
        rwLock.writeLock().lock();//添加写锁
        try {
            System.out.println(Thread.currentThread().getName() + "正在进行写操作" + key);
            TimeUnit.MILLISECONDS.sleep(300);//模拟写入过程
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "写完了" + key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            rwLock.writeLock().unlock();//释放写锁
        }
    }
    public Object get(String key){
        rwLock.readLock().lock();//添加读锁
        Object result = null;
        try {
            System.out.println(Thread.currentThread().getName() + "正在进行读操作" + key);
            TimeUnit.MILLISECONDS.sleep(300);//模拟写入过程
            result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "读完了" + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            rwLock.readLock().lock();//释放锁
        }
        return result;
    }
}
