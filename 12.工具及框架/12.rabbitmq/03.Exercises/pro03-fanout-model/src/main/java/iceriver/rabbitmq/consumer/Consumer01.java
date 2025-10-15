package iceriver.rabbitmq.consumer;

import com.rabbitmq.client.*;
import iceriver.rabbitmq.utils.RabbitMQUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;

/**
 * @author zhuyuqi
 * @version v0.0.1
 * @className Consumer
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/08/28 21:41
 */
public class Consumer01 extends Thread {
    private final static String EXCHANGE_NAME = "fanout_exchange";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMQUtils.getChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");//创建一个交换机
        String queue = channel.queueDeclare().getQueue();//创建一个临时队列
        channel.queueBind(queue, EXCHANGE_NAME, "");//将临时队列与交换机绑定，routingKey是一个空串。
        System.out.println("等待接收消息,把接收到的消息写入文件.....");
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            File file = new File("D:\\work\\rabbitmq_info.txt");
            FileUtils.writeStringToFile(file,message,"UTF-8");
            System.out.println("数据写入文件成功");
        };
        channel.basicConsume(queue, true, deliverCallback, consumerTag -> { });
    }
}
