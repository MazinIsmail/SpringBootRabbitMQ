package com.rabbitmq.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
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
public class RabbitMQFanoutConfig {

	@Value("${springboot.rabbitmq.queueNameFanoutOne}")
	String queueNameFanoutOne;

	@Value("${springboot.rabbitmq.queueNameFanoutTwo}")
	String queueNameFanoutTwo;

	@Value("${springboot.rabbitmq.queueNameFanoutThree}")
	String queueNameFanoutThree;

	@Value("${springboot.rabbitmq.exchangeNameFanout}")
	String exchangeNameFanout;

	@Value("${springboot.rabbitmq.routingKeyFanout}")
	private String routingKeyFanout;

	@Bean(name = "queueFanoutOne")
	Queue queueFanoutOne() {
		return new Queue(queueNameFanoutOne, false);
	}

	@Bean(name = "queueFanoutTwo")
	Queue queueFanoutTwo() {
		return new Queue(queueNameFanoutTwo, false);
	}

	@Bean(name = "queueFanoutThree")
	Queue queueFanoutThree() {
		return new Queue(queueNameFanoutThree, false);
	}

	// Only one exchange.
	@Bean
	FanoutExchange exchangeFanout() {
		return new FanoutExchange(exchangeNameFanout);
	}

	@Bean
	Binding bindingFanoutOne(@Qualifier("queueFanoutOne") Queue queueFanoutOne, FanoutExchange exchangeFanout) {
		return BindingBuilder.bind(queueFanoutOne).to(exchangeFanout);
	}

	@Bean
	Binding bindingFanoutTwo(@Qualifier("queueFanoutTwo") Queue queueFanoutTwo, FanoutExchange exchangeFanout) {
		return BindingBuilder.bind(queueFanoutTwo).to(exchangeFanout);
	}

	@Bean
	Binding bindingFanoutThree(@Qualifier("queueFanoutThree") Queue queueFanoutThree,
			FanoutExchange exchangeFanout) {
		return BindingBuilder.bind(queueFanoutThree).to(exchangeFanout);
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

	@Bean(name = "rabbitTemplateFanout")
	public AmqpTemplate rabbitTemplateFanout(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(jsonMessageConverter());
		return rabbitTemplate;
	}

}
