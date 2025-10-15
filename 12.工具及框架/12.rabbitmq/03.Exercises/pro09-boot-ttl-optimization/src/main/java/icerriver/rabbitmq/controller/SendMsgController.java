package icerriver.rabbitmq.controller;

import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("ttl")
@Slf4j
public class SendMsgController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("sendMsg/{message}")
    public void sendMsg(@PathVariable String message){
        log.info("当前时间为{}，发送一条信息给两个队列{}", new Date().toString(), message);
        rabbitTemplate.convertAndSend("X", "XA","消息来自ttl为10s的队列" + message);
        rabbitTemplate.convertAndSend("X", "XB","消息来自ttl为40s的队列" + message);
    }
    @GetMapping("sendExpirationMsg/{message}/{ttlTime}")
    public void sendMsg(@PathVariable String message,
                        @PathVariable String ttlTime){
        log.info("当前时间为{}，发送一条信息时长{}毫秒的消息{}", new Date().toString(),ttlTime, message);
        rabbitTemplate.convertAndSend("X", "XC", message, msg ->{
            msg.getMessageProperties().setExpiration(ttlTime);
            return msg;
        });
    }
}
