package com.rabbitmq.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rabbitmq.model.Employee;

@Service
public class RabbitMQFanoutSender {

	@Autowired
	@Qualifier("rabbitTemplateFanout")
	private AmqpTemplate rabbitTemplateFanout;

	@Value("${springboot.rabbitmq.exchangeNameFanout}")
	private String exchangeNameFanout;

	public void sendFanout(Employee employee) {
		/**
		 * As this is a fanout exchange, we do not need to specify a binding key.
		 */
		rabbitTemplateFanout.convertAndSend(exchangeNameFanout, "", employee);
		System.out.println("Send Fanout: " + employee);
		System.out.println("Fanout exchangeNameFanout: " + exchangeNameFanout);
		System.out.println("Fanout routingKeyTopic: ");
		System.out.println();
	}
}