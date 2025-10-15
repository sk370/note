package iceriver.rabbitmq.consumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import iceriver.rabbitmq.utils.RabbitMQUtils;

/**
 * @author zhuyuqi
 * @version v0.0.1
 * @className Consumer
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/08/28 21:41
 */
public class Consumer03 extends Thread {
    private final static String EXCHANGE_NAME = "direct_exchange";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMQUtils.getChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");//创建一个交换机
        channel.queueDeclare("disk", false, false, false, null);
        channel.queueBind("disk", EXCHANGE_NAME, "error");//将临时队列与交换机绑定，routingKey是一个空串。
        System.out.println("等待接收消息,把接收到的消息打印到控制台.....");
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println("控制台打印接收到的消息" + message);
        };
        channel.basicConsume("console", true, deliverCallback, consumerTag -> { });
    }
}
