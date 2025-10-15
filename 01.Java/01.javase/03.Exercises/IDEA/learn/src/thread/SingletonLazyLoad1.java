package thread;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/5/30 14:14
 */
public class SingletonLazyLoad1 {
    private static SingletonLazyLoad1 singletonLazyLoad1 = null;
    private SingletonLazyLoad1 (){    }
    public static SingletonLazyLoad1 getSingletonLazyLoad1(){
        if(singletonLazyLoad1 == null) {
            synchronized (SingletonLazyLoad1.class) {
                if (singletonLazyLoad1 == null) {
                    singletonLazyLoad1 = new SingletonLazyLoad1();
                }
            }
        }
        return singletonLazyLoad1;
    }
}
class SingletonThread1 extends Thread{
    @Override
    public void run() {
        SingletonLazyLoad1 s =SingletonLazyLoad1.getSingletonLazyLoad1();
        System.out.println(s);
    }
}
class SingletonTest1{
    public static void main(String[] args) {
        SingletonThread1 singletonThread11 = new SingletonThread1();
        SingletonThread1 singletonThread12 = new SingletonThread1();
        singletonThread11.start();
        singletonThread12.start();
    }
}