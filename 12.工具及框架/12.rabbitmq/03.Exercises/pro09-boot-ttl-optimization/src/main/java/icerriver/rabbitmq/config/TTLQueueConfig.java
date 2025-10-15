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
 * @className TTLQueueConfig
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/08/30 07:43
 */
@Configuration
public class TTLQueueConfig {
    public static final String X_EXCHANGE = "X";
    public static final String Y_DEAD_LETTER_EXCHANGE = "Y";
    public static final String QUEUEA = "QA";
    public static final String QUEUEB = "QB";
    public static final String QUEUEC = "QC";//不设置过期时间的队列
    public static final String DEAD_LETTER_QUEUE = "QD";//死信队列

    /**
     * 声明普通交换机
     * @return
     */
    @Bean("xExchange")//注入时可以使用别名
    public DirectExchange xExchange(){
        return new DirectExchange(X_EXCHANGE);
    }
    /**
     * 声明死信交换机
     * @return
     */
    @Bean("yExchange")//注入时可以使用别名
    public DirectExchange yExchange(){
        return new DirectExchange(Y_DEAD_LETTER_EXCHANGE);
    }
    /**
     * 声明普通队列
     * @return
     */
    @Bean("queueA")//注入时可以使用别名
    public Queue queueA(){
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange", Y_DEAD_LETTER_EXCHANGE);//设置死信交换机
        arguments.put("x-dead-letter-routing-key", "YD");//设置死信ROUTITNGKEY
        arguments.put("x-message-ttl", 10000);//设置消息过期时间
        return QueueBuilder.durable(QUEUEA).withArguments(arguments).build();
    }
    /**
     * 声明普通队列
     * @return
     */
    @Bean("queueB")//注入时可以使用别名
    public Queue queueB(){
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange", Y_DEAD_LETTER_EXCHANGE);//设置死信交换机
        arguments.put("x-dead-letter-routing-key", "YD");//设置死信ROUTITNGKEY
        arguments.put("x-message-ttl", 40000);//设置消息过期时间
        return QueueBuilder.durable(QUEUEB).withArguments(arguments).build();
    }
    /**
     * 声明普通队列，并不设置过期时间
     * @return
     */
    @Bean("queueC")//注入时可以使用别名
    public Queue queueC(){
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange", Y_DEAD_LETTER_EXCHANGE);
        arguments.put("x-dead-letter-routing-key", "YD");
        return QueueBuilder.durable(QUEUEC).withArguments(arguments).build();
    }
    /**
     * 声明死信队列
     * @return
     */
    @Bean("queueD")//注入时可以使用别名
    public Queue queueD(){
        return QueueBuilder.durable(DEAD_LETTER_QUEUE).build();
    }

    /**
     * 队列A绑定普通交换机
     * @param queueA
     * @param xExchange
     * @return
     */
    @Bean
    public Binding queueABindingX(@Qualifier("queueA") Queue queueA,
                                  @Qualifier("xExchange") DirectExchange xExchange){
        return BindingBuilder.bind(queueA).to(xExchange).with("XA");//with()参数XA为routingkey
    }
    /**
     * 队列B绑定普通交换机
     * @param queueB
     * @param xExchange
     * @return
     */
    @Bean
    public Binding queueABindingB(@Qualifier("queueB") Queue queueB,
                                  @Qualifier("xExchange") DirectExchange xExchange){
        return BindingBuilder.bind(queueB).to(xExchange).with("XB");//with()参数XB为routingkey
    }
    /**
     * 队列C绑定普通交换机
     * @param queueC
     * @param xExchange
     * @return
     */
    @Bean
    public Binding queueABindingC(@Qualifier("queueC") Queue queueC,
                                  @Qualifier("xExchange") DirectExchange xExchange){
        return BindingBuilder.bind(queueC).to(xExchange).with("XC");//with()参数XC为routingkey
    }
    /**
     * 队列D绑定死信交换机
     * @param queueD
     * @param yExchange
     * @return
     */
    @Bean
    public Binding queueABindingD(@Qualifier("queueD") Queue queueD,
                                  @Qualifier("yExchange") DirectExchange yExchange){
        return BindingBuilder.bind(queueD).to(yExchange).with("YD");//with()参数XD为routingkey
    }
}
