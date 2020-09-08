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

	/**
	 * When using RabbitMQ the publisher never directly sends a message to a queue.
	 * Instead, the publisher sends messages to an exchange. Exchange is responsible
	 * for sending the message to an appropriate queue based on routing keys,
	 * bindings and header attributes. Exchanges are message routing agents which we
	 * can define and bindings are what connects the exchanges to queues.
	 * 
	 * With RabbitMQ we have the following types of Exchanges:-
	 * 
	 * Direct Exchange
	 * 
	 * Fanout Exchange
	 * 
	 * Topic Exchange
	 * 
	 * Header Exchange
	 * 
	 * 
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
