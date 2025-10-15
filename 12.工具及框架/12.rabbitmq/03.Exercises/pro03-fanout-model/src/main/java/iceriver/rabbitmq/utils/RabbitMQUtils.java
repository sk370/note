package iceriver.rabbitmq.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author zhuyuqi
 * @version v0.0.1
 * @className RabbitMQUtils
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/08/28 21:56
 */
public class RabbitMQUtils {
    private static Connection connection = null;
    private static Channel channel = null;

    /**
     * 得到一个通道
     * @return
     * @throws Exception
     */
    public static Channel getChannel() {
        try {
            ConnectionFactory factory = new ConnectionFactory();//创建一个连接工厂
            factory.setHost("192.168.150.100");//连接rabbitmq主机
//        factory.setPort(5672);//设置端口号，默认5672可以则可以不写这句
//        factory.setVirtualHost("/");//设置连接哪个虚拟主机，默认虚拟主机/时可以不写这句
            factory.setUsername("admin");
            factory.setPassword("123");

            connection = factory.newConnection();//获取连接对象
            channel = connection.createChannel();//获取连接中通道
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return channel;
    }
    public static void close(){
        if (channel != null){
            try {
                channel.close();
            } catch (IOException | TimeoutException e) {
                e.printStackTrace();
            }

        }

        if (connection != null){
            try {
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
