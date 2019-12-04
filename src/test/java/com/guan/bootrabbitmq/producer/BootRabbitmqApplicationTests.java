package com.guan.bootrabbitmq.producer;


import com.guan.bootrabbitmq.config.RabbitMqConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BootRabbitmqApplicationTests {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	//使用rabbitTemplate发送消息
	@Test
	public void testSendEmail() {

		String message = "这是邮件";
		/**
		 * 1. 交换机
		 * 2. routingKey
		 * 3. 消息内容
		 */
		rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE_TOPICS_INFO, "info.email", message);
	}

	@Test
	public void testSendSms() {

		String message = "这是短信";

		rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE_TOPICS_INFO, "info.sms", message);
	}


}
