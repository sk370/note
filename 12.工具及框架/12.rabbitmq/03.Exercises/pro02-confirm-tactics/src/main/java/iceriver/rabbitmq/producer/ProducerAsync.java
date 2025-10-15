package iceriver.rabbitmq.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmCallback;
import iceriver.rabbitmq.utils.RabbitMQUtils;

/**
 * @author zhuyuqi
 * @version v0.0.1
 * @className ProducerMultiple
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/08/29 15:16
 */
public class ProducerAsync {
    private final static String QUEUE_NAME = "confirm_single";
    private final static int MESSAGE_COUNT = 1000;

    public static void main(String[] args) throws Exception {
        ProducerAsync.publishMessageAsync();
    }

    public static void publishMessageAsync() throws Exception {
        Channel channel = RabbitMQUtils.getChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //开启发布确认
        channel.confirmSelect();

        ConfirmCallback ackCallback = (deliverTag, multiple) ->{
            System.out.println("确认的消息"+deliverTag);
        };
        ConfirmCallback nackCallback = (deliverTag, multiple) -> {
            System.out.println("未确认的消息"+deliverTag);
        };
        /**
         * 添加一个异步确认的监听器
         * 1.确认收到消息的回调
         * 2.未收到消息的回调
         */
        channel.addConfirmListener(ackCallback, nackCallback);
        long begin = System.currentTimeMillis();
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message = i + "";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        }
        long end = System.currentTimeMillis();
        System.out.println("发布" + MESSAGE_COUNT + "个异步确认消息,耗时" + (end - begin) + "ms");
        RabbitMQUtils.close();
    }
}
