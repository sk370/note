package iceriver.rabbitmq.consumer;

import com.rabbitmq.client.*;

/**
 * @author zhuyuqi
 * @version v0.0.1
 * @className Consumer
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/08/28 21:41
 */
public class Consumer {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();//创建一个连接工厂
        factory.setHost("192.168.150.100");//连接rabbitmq主机
//        factory.setPort(5672);//设置端口号，默认5672可以则可以不写这句
//        factory.setVirtualHost("/");//设置连接哪个虚拟主机，默认虚拟主机/时可以不写这句
        factory.setUsername("admin");
        factory.setPassword("123");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        System.out.println("等待接收消息....");
        //推送的消息如何进行消费的接口回调
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody());
            System.out.println(message);
        };
        //取消消费的一个回调接口 如在消费的时候队列被删除掉了
        CancelCallback cancelCallback = (consumerTag) -> {
            System.out.println("消息消费被中断");
        };
        /**
         * 消费者消费消息
         * 1.消费哪个队列
         * 2.消费成功之后是否要自动应答 true 代表自动应答 【false 手动应答】
         * 3.消费者成功消费的回调
         * 4.消费者取消消费的回调
         */
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, cancelCallback);
        channel.close();
        connection.close();
    }
}
