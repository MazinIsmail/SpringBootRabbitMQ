package com.rabbitmq.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQTopicConfig {

	@Value("${springboot.rabbitmq.queueNameTopicOne}")
	String queueNameTopicOne;

	@Value("${springboot.rabbitmq.queueNameTopicTwo}")
	String queueNameTopicTwo;

	@Value("${springboot.rabbitmq.queueNameTopicThree}")
	String queueNameTopicThree;

	@Value("${springboot.rabbitmq.queueNameTopicAllQueue}")
	String queueNameTopicAllQueue;

	@Value("${springboot.rabbitmq.exchangeNameTopic}")
	String exchangeNameTopic;

	@Value("${springboot.rabbitmq.routingKeyTopicOne}")
	private String routingKeyTopicOne;

	@Value("${springboot.rabbitmq.routingKeyTopicTwo}")
	private String routingKeyTopicTwo;

	@Value("${springboot.rabbitmq.routingKeyTopicThree}")
	private String routingKeyTopicThree;

	@Value("${springboot.rabbitmq.routingKeyTopicAll}")
	private String routingKeyTopicAll;

	@Bean(name = "queueTopicOne")
	Queue queueTopicOne() {
		return new Queue(queueNameTopicOne, false);
	}

	@Bean(name = "queueTopicTwo")
	Queue queueTopicTwo() {
		return new Queue(queueNameTopicTwo, false);
	}

	@Bean(name = "queueTopicThree")
	Queue queueTopicThree() {
		return new Queue(queueNameTopicThree, false);
	}

	@Bean(name = "queueTopicAllQueue")
	Queue queueTopicAllQueue() {
		return new Queue(queueNameTopicAllQueue, false);
	}

	// Only one exchange.
	@Bean
	TopicExchange topicExchange() {
		return new TopicExchange(exchangeNameTopic);
	}

	@Bean
	Binding bindingTopicOne(@Qualifier("queueTopicOne") Queue queueTopicOne, TopicExchange topicExchange) {
		return BindingBuilder.bind(queueTopicOne).to(topicExchange).with(routingKeyTopicOne);
	}

	@Bean
	Binding bindingTopicTwo(@Qualifier("queueTopicTwo") Queue queueTopicTwo, TopicExchange topicExchange) {
		return BindingBuilder.bind(queueTopicTwo).to(topicExchange).with(routingKeyTopicTwo);
	}

	@Bean
	Binding bindingTopicThree(@Qualifier("queueTopicThree") Queue queueTopicThree, TopicExchange topicExchange) {
		return BindingBuilder.bind(queueTopicThree).to(topicExchange).with(routingKeyTopicThree);
	}

	@Bean
	Binding allBinding(@Qualifier("queueTopicAllQueue") Queue queueTopicAllQueue, TopicExchange topicExchange) {
		return BindingBuilder.bind(queueTopicAllQueue).to(topicExchange).with(routingKeyTopicAll);
	}

	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	MessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {
		SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
		simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
		return simpleMessageListenerContainer;
	}

	@Bean(name = "rabbitTemplateTopic")
	public AmqpTemplate rabbitTemplateTopic(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(jsonMessageConverter());
		return rabbitTemplate;
	}

}
