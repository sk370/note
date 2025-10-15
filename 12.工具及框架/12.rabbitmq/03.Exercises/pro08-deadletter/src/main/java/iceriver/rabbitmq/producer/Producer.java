package iceriver.rabbitmq.producer;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import iceriver.rabbitmq.utils.RabbitMQUtils;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhuyuqi
 * @version v0.0.1
 * @className Producer
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/08/28 21:03
 */
public class Producer {
    private final static String NORMAL_EXCHANGE = "normal_exchange";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMQUtils.getChannel();
        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder().expiration("10000").build();//设置过期时间
        for (int i = 0; i < 10; i++) {
            String message  = "info" + (i + 1);
            channel.basicPublish(NORMAL_EXCHANGE, "zhangsan", properties, message.getBytes(StandardCharsets.UTF_8));
        }
        RabbitMQUtils.close();
    }
}
