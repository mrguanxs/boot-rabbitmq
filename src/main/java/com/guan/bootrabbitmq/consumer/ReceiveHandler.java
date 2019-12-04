package com.guan.bootrabbitmq.consumer;

import com.guan.bootrabbitmq.config.RabbitMqConfig;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author mrguanxs@163.com
 * @date 2019/12/4
 * 消息接收者
 */
@Component
public class ReceiveHandler {

    @RabbitListener(queues = {RabbitMqConfig.QUEUE_INFO_EMAIL})
    public void receiveEmail(String message){
        System.out.println("接收到邮件消息:" + message);
    }

    @RabbitListener(queues = {RabbitMqConfig.QUEUE_INFO_SMS})
    public void receiveSMS(Message message, Channel channel){
        System.out.println("接收到短信消息:" + message.getBody().toString() +"通道是:" + channel.toString());
    }

//    @RabbitListener(queues = {"info.email.sms"})
//    public void receiveSMS_EMAIL(Message message, Channel channel){
//        System.out.println("我什么消息都接:" + message.getBody().toString() +"通道是:" + channel.toString());
//    }
}
