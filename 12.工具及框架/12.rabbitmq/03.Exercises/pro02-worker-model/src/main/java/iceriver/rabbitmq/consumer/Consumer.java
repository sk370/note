package iceriver.rabbitmq.consumer;

import com.rabbitmq.client.*;
import iceriver.rabbitmq.utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @author zhuyuqi
 * @version v0.0.1
 * @className Consumer
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/08/28 21:41
 */
public class Consumer extends Thread {
    @Override
    public void run() {
        try {
            Channel channel = RabbitMQUtils.getChannel();
            String name = Thread.currentThread().getName();
            System.out.println( name + "等待接收消息....");
            //推送的消息如何进行消费的接口回调
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody());
                System.out.println(name + "消费了一条消息，内容是" + message);
            };
            //取消消费的一个回调接口 如在消费的时候队列被删除掉了
            CancelCallback cancelCallback = (consumerTag) -> {
                System.out.println("消息消费被中断");
            };/**
             * 消费者消费消息
             * 1.消费哪个队列
             * 2.消费成功之后是否要自动应答 true 代表自动应答 【false 手动应答】
             * 3.消费者成功消费的回调
             * 4.消费者取消消费的回调
             */
            channel.basicConsume(QUEUE_NAME, true, deliverCallback, cancelCallback);
        }  catch (Exception e) {
            e.printStackTrace();
        }
        RabbitMQUtils.close();
    }

    private final static String QUEUE_NAME = "worker";

    public static void main(String[] args) throws Exception {
        Consumer thread1 = new Consumer();
        Consumer thread2 = new Consumer();
        Consumer thread3 = new Consumer();
        thread1.setName("消费者1");
        thread2.setName("消费者2");
        thread3.setName("消费者3");
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
