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
    public static final String CONFIRM_EXCHANGE = "confirm.exchange";
    public static final String CONFIRM_QUEUE = "confirm.queue";
    public static final String CONFIRM_ROUTING_KEY = "key1";
    public static final String BACKUP_EXCHANGE = "backup.exchange";
    public static final String BACKUP_QUEUE = "backup.queue";
    public static final String WARNING_QUEUE = "warning.queue";

    /**
     * 声明交换机
     * @return
     */
    @Bean("confirmExchange")
    public DirectExchange confirmExchange(){
        return ExchangeBuilder.directExchange(CONFIRM_EXCHANGE).withArgument("alternate-exchange", BACKUP_EXCHANGE).build();
    }

    /**
     * 声明普通队列
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

    /**
     * 声明备份交换机
     * @return
     */
    @Bean("backupExchange")
    public FanoutExchange backupExchange(){
        return new FanoutExchange(BACKUP_EXCHANGE);
    }

    /**
     * 声明备份队列
     * @return
     */
    @Bean("backupQueu")//注入时可以使用别名
    public Queue backupQueu(){
        return QueueBuilder.durable(BACKUP_QUEUE).build();
    }
    /**
     * 声明警告队列
     * @return
     */
    @Bean("warningQueue")//注入时可以使用别名
    public Queue warningQueue(){
        return QueueBuilder.durable(WARNING_QUEUE).build();
    }
    /**
     * 备份交换机绑定备份队列
     * @param backupQueu
     * @param backupExchange
     * @return
     */
    @Bean
    public Binding backupQueuBindingBackupExchange(@Qualifier("backupQueu") Queue backupQueu,
                                                   @Qualifier("backupExchange") FanoutExchange backupExchange){
        return BindingBuilder.bind(backupQueu).to(backupExchange);
    }
    /**
     * 交换机绑定队列
     * @param warningQueue
     * @param backupExchange
     * @return
     */
    @Bean
    public Binding backupQueuBindingWarningQueue(@Qualifier("warningQueue") Queue warningQueue,
                                                 @Qualifier("backupExchange") FanoutExchange backupExchange){
        return BindingBuilder.bind(warningQueue).to(backupExchange);
    }
}
