package iceriver.rabbitmq.consumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import iceriver.rabbitmq.utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @author zhuyuqi
 * @version v0.0.1
 * @className Consumer2
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/08/29 22:08
 */
public class Consumer2 {

    private final static String DEAD_QUEUE = "dead_queue";
    public static void main(String[] args) throws IOException {
        Channel channel = RabbitMQUtils.getChannel();
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println("接收到的消息" + message);
        };
        channel.basicConsume(DEAD_QUEUE, true, deliverCallback, consumerTag -> { });
    }
}
