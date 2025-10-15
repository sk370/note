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

    public static final String DELAYED_EXCHANGE_NAME = "delayed.exchange";
    public static final String DELAYED_ROUTING_KEY = "delayed.routing.key";

    @GetMapping("sendDelayMsg/{message}/{delayTime}")
    public void sendMsg(@PathVariable String message,@PathVariable Integer delayTime) {
        rabbitTemplate.convertAndSend(DELAYED_EXCHANGE_NAME, DELAYED_ROUTING_KEY, message,
                correlationData -> {
                    correlationData.getMessageProperties().setDelay(delayTime);
                    return correlationData;
                });
        log.info(" 当 前 时 间 ： {}, 发 送 一 条 延 迟 {} 毫 秒 的 信 息 给 队 列 delayed.queue:{}", new Date(),delayTime, message);
    }
}
