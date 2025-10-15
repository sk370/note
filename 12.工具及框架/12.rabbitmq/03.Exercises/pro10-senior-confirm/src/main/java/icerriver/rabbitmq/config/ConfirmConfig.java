package icerriver.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhuyuqi
 * @version v0.0.1
 * @className ConfirmConfig
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/08/30 09:45
 */
@Configuration
public class ConfirmConfig {
    public static final String CONFIRM_QUEUE = "confirm.queue";
    public static final String CONFIRM_EXCHANGE = "confirm.exchange";
    public static final String CONFIRM_ROUTING_KEY = "key1";

    /**
     * 声明交换机
     * @return
     */
    @Bean("confirmExchange")
    public DirectExchange confirmExchange(){
        return new DirectExchange(CONFIRM_EXCHANGE);
    }

    /**
     * 声明队列——即普通队列
     * @return
     */
    @Bean("confirmQueue")//注入时可以使用别名
    public Queue confirmQueue(){
        return QueueBuilder.durable(CONFIRM_QUEUE).build();
    }

    /**
     * 交换机绑定队列
     * @param confirmQueue
     * @param confirmExchange
     * @return
     */
    @Bean
    public Binding queueDelayedExchageBindingQueue(@Qualifier("confirmQueue") Queue confirmQueue,
                                                   @Qualifier("confirmExchange") DirectExchange confirmExchange){
        return BindingBuilder.bind(confirmQueue).to(confirmExchange).with(CONFIRM_ROUTING_KEY);
    }
}
