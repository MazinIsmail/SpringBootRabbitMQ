package com.rabbitmq.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rabbitmq.model.Employee;

@Service
public class RabbitMQTopicSender {

	@Autowired
	@Qualifier("rabbitTemplateTopic")
	private AmqpTemplate amqpTemplateTopic;

	@Value("${springboot.rabbitmq.exchangeNameTopic}")
	private String exchangeNameTopic;

	@Value("${springboot.rabbitmq.routingKeyTopicOne}")
	private String routingKeyTopicOne;

	@Value("${springboot.rabbitmq.routingKeyTopicTwo}")
	private String routingKeyTopicTwo;

	@Value("${springboot.rabbitmq.routingKeyTopicThree}")
	private String routingKeyTopicThree;

	@Value("${springboot.rabbitmq.routingKeyTopicAll}")
	private String routingKeyTopicAll;

	public void sendTopic(Employee employee) {
		amqpTemplateTopic.convertAndSend(exchangeNameTopic, routingKeyTopicOne, employee);
		System.out.println("Send Topic: " + employee);
		System.out.println("Topic exchangeNameTopic: " + exchangeNameTopic);
		System.out.println("Topic routingKeyTopic: " + routingKeyTopicOne);
		System.out.println();

		amqpTemplateTopic.convertAndSend(exchangeNameTopic, routingKeyTopicTwo, employee);
		System.out.println("Send Topic: " + employee);
		System.out.println("Topic exchangeNameTopic: " + exchangeNameTopic);
		System.out.println("Topic routingKeyTopic: " + routingKeyTopicTwo);
		System.out.println();

		amqpTemplateTopic.convertAndSend(exchangeNameTopic, routingKeyTopicThree, employee);
		System.out.println("Send Topic: " + employee);
		System.out.println("Topic exchangeNameTopic: " + exchangeNameTopic);
		System.out.println("Topic routingKeyTopic: " + routingKeyTopicThree);
		System.out.println();

		amqpTemplateTopic.convertAndSend(exchangeNameTopic, routingKeyTopicAll, employee);
		System.out.println("Send Topic: " + employee);
		System.out.println("Topic exchangeNameTopic: " + exchangeNameTopic);
		System.out.println("Topic routingKeyTopic: " + routingKeyTopicAll);
		System.out.println();
	}
}