package top.sk370.multi_thread_lock;

import java.util.concurrent.TimeUnit;

/**
 * @author zhuyuqi
 * @version v0.0.1
 * @className Lock_8
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/10/03 16:21
 */

/**
 * @Description: 8锁
 *
1 标准访问，先打印短信还是邮件
------sendSMS
------sendEmail

2 停4秒在短信方法内，先打印短信还是邮件
------sendSMS
------sendEmail

3 新增普通的hello方法，是先打短信还是hello
------getHello
------sendSMS

4 现在有两部手机，先打印短信还是邮件
------sendEmail
------sendSMS

5 两个静态同步方法，1部手机，先打印短信还是邮件
------sendSMS
------sendEmail

6 两个静态同步方法，2部手机，先打印短信还是邮件
------sendSMS
------sendEmail

7 1个静态同步方法,1个普通同步方法，1部手机，先打印短信还是邮件
------sendEmail
------sendSMS

8 1个静态同步方法,1个普通同步方法，2部手机，先打印短信还是邮件
------sendEmail
------sendSMS

 */
public class Lock_8 {
    public static void main(String[] args) throws Exception {
        Phone phone = new Phone();
        Phone phone2 = new Phone();

        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "AA").start();

        Thread.sleep(100);//由于线程何时启动不确定，增加此句便于观察程序运行

        new Thread(() -> {
            try {
                // phone.sendEmail();
                // phone.getHello();
                phone2.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "BB").start();
    }
}
class Phone {
    public static synchronized void sendSMS() throws Exception {
        TimeUnit.SECONDS.sleep(4);
        System.out.println("------sendSMS");
    }

    public synchronized void sendEmail() throws Exception {
        System.out.println("------sendEmail");
    }

    public void getHello() {
        System.out.println("------getHello");
    }
}