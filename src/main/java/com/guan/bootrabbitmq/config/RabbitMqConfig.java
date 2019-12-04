package com.guan.bootrabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mrguanxs@163.com
 * @date 2019/12/4
 * 声明队列和交换机
 */
@Configuration
public class RabbitMqConfig {
    public static final String QUEUE_INFO_EMAIL = "queue_info_email";
    public static final String QUEUE_INFO_SMS = "queue_info_sms";
    public static final String EXCHANGE_TOPICS_INFO = "exchange_topics_info";
    public static final String ROUTINGKEY_EMAIL = "info.#.email.#";
    public static final String ROUTINGKEY_SMS = "info.#.sms.#";

    //声明交换机
    @Bean(EXCHANGE_TOPICS_INFO)
    public Exchange EXCHANGE_TOPICS_INFO(){
        return ExchangeBuilder.topicExchange(EXCHANGE_TOPICS_INFO).durable(true).build();
    }

    //声明QUEUE_INFO_EMAIL队列
    @Bean(QUEUE_INFO_EMAIL)
    public Queue QUEUE_INFO_EMAIL(){
        return new Queue(QUEUE_INFO_EMAIL);
    }
    //声明QUEUE_INFO_SMS队列
    @Bean(QUEUE_INFO_SMS)
    public Queue QUEUE_INFO_SMS(){
        return new Queue(QUEUE_INFO_SMS);
    }

    //队列绑定交换机,指定routingKey
    @Bean
    public Binding Binding_QUEUE_INFO_EMAIL(@Qualifier(QUEUE_INFO_EMAIL) Queue queue,
                                            @Qualifier(EXCHANGE_TOPICS_INFO) Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(ROUTINGKEY_EMAIL).noargs();
    }
    @Bean
    public Binding Binding_QUEUE_INFO_SMS(@Qualifier(QUEUE_INFO_SMS) Queue queue,
                                            @Qualifier(EXCHANGE_TOPICS_INFO) Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(ROUTINGKEY_SMS).noargs();
    }

}
