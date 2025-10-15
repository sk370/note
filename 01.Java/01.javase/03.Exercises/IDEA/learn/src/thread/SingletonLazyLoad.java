package thread;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/5/30 14:14
 */
public class SingletonLazyLoad {
    private static SingletonLazyLoad singletonLazyLoad = null;
    private SingletonLazyLoad (){    }
    public synchronized static SingletonLazyLoad getSingletonLazyLoad(){
//    public static SingletonLazyLoad getSingletonLazyLoad(){ //有线程安全问题
        if(singletonLazyLoad == null){
                singletonLazyLoad = new SingletonLazyLoad();
        }
        return singletonLazyLoad;
    }
}
class SingletonThread extends Thread{
    @Override
    public void run() {
        SingletonLazyLoad s =SingletonLazyLoad.getSingletonLazyLoad();
        System.out.println(s);
    }
}
class SingletonTest{
    public static void main(String[] args) {
        SingletonThread singletonThread1 = new SingletonThread();
        SingletonThread singletonThread2 = new SingletonThread();
        singletonThread1.start();
        singletonThread2.start();
    }
}