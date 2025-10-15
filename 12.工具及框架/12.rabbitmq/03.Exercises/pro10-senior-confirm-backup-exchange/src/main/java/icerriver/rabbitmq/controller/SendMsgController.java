package icerriver.rabbitmq.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author zhuyuqi
 * @version v0.0.1
 * @className SendMsgController
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/08/30 08:04
 */
@RestController
@RequestMapping("confirm")
@Slf4j
public class SendMsgController {
    public static final String CONFIRM_EXCHANGE = "confirm.exchange";
    public static final String CONFIRM_ROUTING_KEY = "key1";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("sendMessage/{message}")
    public void sendMsg(@PathVariable String message){
        CorrelationData correlationData = new CorrelationData("10");
        log.info("当前时间为{}，发送一条信息给队列{}", new Date().toString(), message);
        rabbitTemplate.convertAndSend(CONFIRM_EXCHANGE, CONFIRM_ROUTING_KEY,"发送消息内容:" + message, correlationData);//正确的发送
        rabbitTemplate.convertAndSend(CONFIRM_EXCHANGE, CONFIRM_ROUTING_KEY + "2","发送消息内容:" + message, correlationData);//错误的发送
    }
}
