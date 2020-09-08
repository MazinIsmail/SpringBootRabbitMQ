package com.rabbitmq.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.rabbitmq.model.Employee;

@Service
public class RabbitMQDirectListener {

	@RabbitListener(queues = "${springboot.rabbitmq.queueNameDirect}")
	public void recievedMessage(Employee employee) {
		System.out.println("Recieved Direct: " + employee);
		System.out.println();
	}
}