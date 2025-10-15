package iceriver.rabbitmq.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import iceriver.rabbitmq.utils.RabbitMQUtils;

import java.util.Scanner;

/**
 * @author zhuyuqi
 * @version v0.0.1
 * @className Producer
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/08/28 21:03
 */
public class ProducerAck {
    private final static String QUEUE_NAME = "worker_ack";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMQUtils.getChannel();
        /**
         * 生成一个队列，参数解释如下:
         * 1.队列名称
         * 2.队列里面的消息是否持久化 默认消息存储在内存中
         * 3.该队列是否只供一个消费者进行消费 是否进行共享 【true 可以多个消费者消费】
         * 4.是否自动删除 最后一个消费者端开连接以后 该队列是否自动删除 【true 自动删除】
         * 5.其他参数
         */
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while (flag){
            System.out.println("输入要发送的消息内容，按0退出");
            String str = scanner.nextLine();
            if("0".equals(str)){
                break;
            }else{

                /**
                 * 发送一个消息，参数解释如下:
                 * 1.发送到那个交换机
                 * 2.路由的 key 是哪个
                 * 3.其他的参数信息
                 * 4.发送消息的消息体
                 */
                channel.basicPublish("", QUEUE_NAME, null, str.getBytes());//简单模式发送
                System.out.println("本次消息发送完毕");
            }
        }
        System.out.println("输入结束！");
        RabbitMQUtils.close();
    }
}
