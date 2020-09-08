package com.rabbitmq.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rabbitmq.model.Employee;

@Service
public class RabbitMQDirectSender {

	@Autowired
	@Qualifier("rabbitTemplateDirect")
	private AmqpTemplate rabbitTemplateDirect;

	@Value("${springboot.rabbitmq.exchangeNameDirect}")
	private String exchangeNameDirect;

	@Value("${springboot.rabbitmq.routingKeyDirect}")
	private String routingKeyDirect;

	public void sendDirect(Employee employee) {
		rabbitTemplateDirect.convertAndSend(exchangeNameDirect, routingKeyDirect, employee);
		System.out.println("Send Direct: " + employee);
		System.out.println();
	}
}