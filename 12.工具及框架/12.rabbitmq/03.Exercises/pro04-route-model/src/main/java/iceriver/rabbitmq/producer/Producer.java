package iceriver.rabbitmq.producer;

import com.rabbitmq.client.Channel;
import iceriver.rabbitmq.utils.RabbitMQUtils;

import java.util.Scanner;

/**
 * @author zhuyuqi
 * @version v0.0.1
 * @className Producer
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/08/28 21:03
 */
public class Producer {
    private final static String EXCHANGE_NAME = "direct_exchange";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMQUtils.getChannel();
//        channel.exchangeDeclare(EXCHANGE_NAME, "direct");//获取一个交换机

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
                channel.basicPublish(EXCHANGE_NAME, "info", null, str.getBytes());
                channel.basicPublish(EXCHANGE_NAME, "error", null, str.getBytes());
                System.out.println("本次消息发送完毕");
            }
        }
        System.out.println("输入结束！");
        RabbitMQUtils.close();
    }
}
