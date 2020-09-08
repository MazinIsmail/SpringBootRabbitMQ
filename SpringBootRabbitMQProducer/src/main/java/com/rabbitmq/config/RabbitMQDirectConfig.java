package com.rabbitmq.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Direct Exchange :- A direct exchange is an exchange which route messages to
 * queues based on message routing key. The routing key is a message attribute
 * in the message header added by the producer. Producer adds routing key in
 * message header and sends it to direct exchange. After receiving a message,
 * exchange try to match the routing key with the binding key of all the queues
 * bound to it. If match is found, it route the message to the queue whose
 * binding key is matched and if match is not found, it ignores the message.
 */
@Configuration
@EnableRabbit
public class RabbitMQDirectConfig {

	@Value("${springboot.rabbitmq.queueNameDirect}")
	String queueNameDirect;

	@Value("${springboot.rabbitmq.exchangeNameDirect}")
	String exchangeNameDirect;

	@Value("${springboot.rabbitmq.routingKeyDirect}")
	private String routingKeyDirect;

	@Bean
	Queue queueDirect() {
		return new Queue(queueNameDirect, false);
	}

	/*
	 * Direct Exchange:-
	 * 
	 * Based on the routing key a message is sent to the queue having the same
	 * routing key specified in the binding rule. The routing key of exchange and
	 * the binding queue have to be an exact match. A message is sent to exactly one
	 * queue.
	 */
	@Bean
	DirectExchange directExchange() {
		return new DirectExchange(exchangeNameDirect);
	}

	/**
	 * Bindings are what connects the exchanges to queues.
	 */
	@Bean
	Binding binding(Queue queueDirect, DirectExchange directExchange) {
		return BindingBuilder.bind(queueDirect).to(directExchange).with(routingKeyDirect);
	}

	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean(name = "rabbitTemplateDirect")
	public AmqpTemplate rabbitTemplateDirect(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(jsonMessageConverter());
		return rabbitTemplate;
	}
}
