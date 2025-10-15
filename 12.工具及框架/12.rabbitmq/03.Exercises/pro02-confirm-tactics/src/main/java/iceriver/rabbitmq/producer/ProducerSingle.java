package iceriver.rabbitmq.producer;

import com.rabbitmq.client.Channel;
import iceriver.rabbitmq.utils.RabbitMQUtils;

/**
 * @author zhuyuqi
 * @version v0.0.1
 * @className ProducerSingle
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/08/28 21:03
 */
public class ProducerSingle {
    private final static String QUEUE_NAME = "confirm_single";
    private final static int MESSAGE_COUNT = 1000;

    public static void main(String[] args) throws Exception {
        ProducerSingle.publishMessageIndividually();
    }

    public static void publishMessageIndividually() throws Exception {
        Channel channel = RabbitMQUtils.getChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //开启发布确认
        channel.confirmSelect();
        long begin = System.currentTimeMillis();
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message = i + "";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            //服务端返回 false 或超时时间内未返回，生产者可以消息重发
            boolean flag = channel.waitForConfirms();
            if (flag) {
                System.out.println("消息发送成功");
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("发布" + MESSAGE_COUNT + "个单独确认消息,耗时" + (end - begin) + "ms");
        RabbitMQUtils.close();
    }
}
