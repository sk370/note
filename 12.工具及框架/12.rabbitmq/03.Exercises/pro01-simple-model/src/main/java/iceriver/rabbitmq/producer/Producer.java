package iceriver.rabbitmq.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author zhuyuqi
 * @version v0.0.1
 * @className Producer
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/08/28 21:03
 */
public class Producer {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();//创建一个连接工厂
        factory.setHost("192.168.150.100");//连接rabbitmq主机
//        factory.setPort(5672);//设置端口号，默认5672可以则可以不写这句
//        factory.setVirtualHost("/");//设置连接哪个虚拟主机，默认虚拟主机/时可以不写这句
        factory.setUsername("admin");
        factory.setPassword("123");

        Connection connection = factory.newConnection();//获取连接对象
        Channel channel = connection.createChannel();//获取连接中通道

        /**
         * 生成一个队列，参数解释如下:
         * 1.队列名称，不存在时则自动创建
         * 2.队列里面的消息是否持久化 默认消息存储在内存中【true 持久化】
         * 3.exclusive 是否独占队列（只允许当前队列使用）【true 独占队列】
         * 4.是否自动删除 最后一个消费者断开连接以后 该队列是否自动删除 【true 自动删除】
         * 5.其他参数
         */
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "hello world";
        /**
         * 发送一个消息，参数解释如下:
         * 1.发送到那个交换机
         * 2.路由的 key 是哪个
         * 3.其他的参数信息
         * 4.发送消息的消息体
         */
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());//简单模式发送
        System.out.println("消息发送完毕");
        channel.close();
        connection.close();
    }
}
