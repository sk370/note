package icerriver.rabbitmq.listener;

import icerriver.rabbitmq.config.ConfirmConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author zhuyuqi
 * @version v0.0.1
 * @className WarningConsumer
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/08/30 11:17
 */
@Component
@Slf4j
public class WarningConsumer {
    @RabbitListener(queues = ConfirmConfig.WARNING_QUEUE)//接收消息的队列
    public void receiveConfirmMessage(Message message){
        String msg = new String(message.getBody());
        log.warn("报警发现不可路由消息{}", new Date().toString().toString(), msg);
    }
}
