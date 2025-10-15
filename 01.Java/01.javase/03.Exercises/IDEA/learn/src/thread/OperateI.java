package thread;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/1 14:25
 */
public class OperateI {
    public static int i = 0;

    public static void main(String[] args) {
        Add add = new Add();
        Sub sub = new Sub();
        add.start();
        sub.start();
    }
}

class Add extends Thread {
    private int addCount = 0;

    @Override
    public void run() {
        add();
    }

    public int add() {
//        synchronized (OperateI.class) {
            while (addCount < 20) {
                try {
                    sleep(100);
                    OperateI.i++;
                    System.out.println("当前正加，加后为：" + OperateI.i);
                    addCount++;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            return OperateI.i;
//        }
    }
}

class Sub extends Thread {
    private int subCount = 0;

    @Override
    public void run() {
        sub();
    }

    public int sub() {
//        synchronized (OperateI.class) {
            while (subCount < 20) {
                try {
                    sleep(100);
                    OperateI.i--;
                    System.out.println("当前正减，减后为：" + OperateI.i);
                    subCount++;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            return OperateI.i;
//        }
    }
}