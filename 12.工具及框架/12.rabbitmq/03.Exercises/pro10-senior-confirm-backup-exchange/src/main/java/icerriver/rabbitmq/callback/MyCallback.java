package icerriver.rabbitmq.callback;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author zhuyuqi
 * @version v0.0.1
 * @className MyCallback
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/08/30 10:02
 */
@Component
@Slf4j
public class MyCallback implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {
    @Autowired
    RabbitTemplate rabbitTemplate;
    //由于当前类实现的接口是RabbitTemplate内部的，外部不可见，需要将将当前类注入到RabbitTemplate
    @PostConstruct//相当于调用方法
    public void init(){
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
        rabbitTemplate.setMandatory(true);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        String id = correlationData != null ? correlationData.getId() : "";
        if(ack){
            log.info("交换机已经收到了{}的消息",id);
        }else {
            log.info("交换机还未收到id为{}的消息，原因为：{}", id, cause);
        }
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.error(" 消息{},被交换机{}退回，退回原因:{},路由key:{}",new String(message.getBody()),exchange,replyText,routingKey);
    }
}
