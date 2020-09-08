package com.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.rabbitmq*")
public class SpringBootRabbitMQProducer {
	public static void main(String[] args) {
		SpringApplication.run(new Object[] { SpringBootRabbitMQProducer.class }, args);
	}
}