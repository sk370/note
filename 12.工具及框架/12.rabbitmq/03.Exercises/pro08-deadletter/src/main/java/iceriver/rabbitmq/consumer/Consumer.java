package iceriver.rabbitmq.consumer;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import iceriver.rabbitmq.utils.RabbitMQUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhuyuqi
 * @version v0.0.1
 * @className Consumer
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/08/28 21:41
 */
public class Consumer extends Thread {
    private final static String NORMAL_EXCHANGE = "normal_exchange";
    private final static String NORMAL_QUEUE = "normal_queue";
    private final static String DEAD_EXCHANGE = "dead_exchange";
    private final static String DEAD_QUEUE = "dead_queue";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMQUtils.getChannel();
        channel.exchangeDeclare(NORMAL_EXCHANGE, BuiltinExchangeType.DIRECT);//创建一个交换机
        channel.exchangeDeclare(DEAD_EXCHANGE, BuiltinExchangeType.DIRECT);//创建一个交换机

        Map<String, Object> arguments = new HashMap<>();
//        arguments.put("x-dead-ttl",10000);//设置过期时间，当这里不设置时，让生产者生产消息时设置
        arguments.put("x-dead-letter-exchange", DEAD_EXCHANGE);//正常队列设置死信交换机
        arguments.put("x-dead-letter-routing-key", "lisi");//设置死信队列routingkey
//        arguments.put("x-max-length", 6);//设置死信队列数据长度


        channel.queueDeclare(NORMAL_QUEUE, false, false, false, arguments);
        channel.queueDeclare(DEAD_QUEUE, false, false, false, null);

        channel.queueBind(NORMAL_QUEUE, NORMAL_EXCHANGE, "zhangsan");//将普通交换机与普通队列机绑定
        channel.queueBind(DEAD_QUEUE, DEAD_EXCHANGE, "lisi");//将普通交换机与普通队列机绑定

        System.out.println("等待接收消息.....");
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            if("info5".equals(message)){
                System.out.println(message + "被拒绝");
                channel.basicReject(delivery.getEnvelope().getDeliveryTag(),false);//拒绝消息，false表示不返回员队列
            }
            System.out.println("接收到的消息" + message);
        };
        channel.basicConsume(NORMAL_QUEUE, false, deliverCallback, consumerTag -> { });
    }
}
