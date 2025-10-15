package iceriver.rabbitmq.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmCallback;
import iceriver.rabbitmq.utils.RabbitMQUtils;

import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @author zhuyuqi
 * @version v0.0.1
 * @className ProducerSolveMessage
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/08/29 15:45
 */
public class ProducerSolveMessage {
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
        /**
         * 选择一个线程安全有序的一个哈希表，适用于高并发的情况
         * 1.轻松的将序号与消息进行关联
         * 2.轻松批量删除条目 只要给到序列号
         * 3.支持并发访问
         */
        ConcurrentSkipListMap<Long, String> outstandingConfirms = new ConcurrentSkipListMap<>();
        ConfirmCallback ackCallback = (deliverTag, multiple) ->{
            if (multiple) {
                //------------得到所有已确认消息
                ConcurrentNavigableMap<Long, String> confirmed = outstandingConfirms.headMap(deliverTag);
                //------------清除已确认消息
                confirmed.clear();
                }else{
                    //清除确认消息的tag
                    outstandingConfirms.remove(deliverTag);
                }
            System.out.println("确认的消息"+deliverTag);
        };
        ConfirmCallback nackCallback = (deliverTag, multiple) -> {
            String message = outstandingConfirms.get(deliverTag);
            System.out.println("未确认的消息是"+message + "未确认的消息tag是"+deliverTag);
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
            // ------------记录所有已发送消息
            outstandingConfirms.put(channel.getNextPublishSeqNo(), message);
        }
        long end = System.currentTimeMillis();
        System.out.println("发布" + MESSAGE_COUNT + "个异步确认消息,耗时" + (end - begin) + "ms");
        RabbitMQUtils.close();
    }
}
