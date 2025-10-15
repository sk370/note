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
 * @className DelayedExchangeConfig
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/08/30 09:09
 */
@Configuration
public class DelayedExchangeConfig {
    public static final String DELAYED_QUEUE = "delayed.queue";
    public static final String DELAYED_EXCHANGE = "delayed.exchange";
    public static final String DELAYED_ROUTING_KEY = "delayed.routing.key";

    /**
     * 声明延迟交换机
     * @return
     */
    @Bean()
    public CustomExchange delayedExchange(){
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-delayed-type", "direct");
        return new CustomExchange(DELAYED_EXCHANGE, "x-delayed-message", false,false,arguments);
    }

    /**
     * 声明延迟队列——即普通队列
     * @return
     */
    @Bean()//注入时可以使用别名
    public Queue queue(){
        return QueueBuilder.durable(DELAYED_QUEUE).build();
    }

    /**
     * 延迟交换机绑定队列
     * @param queue
     * @param delayedExchange
     * @return
     */
    @Bean
    public Binding queueDelayedExchageBindingQueue(@Qualifier("queue") Queue queue,
                                                   @Qualifier("delayedExchange") CustomExchange delayedExchange){
        return BindingBuilder.bind(queue).to(delayedExchange).with(DELAYED_ROUTING_KEY).noargs();
    }
}
