package icerriver.rabbitmq.listener;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author zhuyuqi
 * @version v0.0.1
 * @className DeadLetterQueueConsumer
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/08/30 08:10
 */
@Slf4j
@Component
public class DeadLetterQueueConsumer {
    @RabbitListener(queues = "QD")//接收消息，队列QD
    public void receiveD(Message message, Channel channel){
        String msg = new String(message.getBody());
        log.info("当前时间为{}，收到的死信队列消息{}", new Date().toString(), msg);
    }
}
