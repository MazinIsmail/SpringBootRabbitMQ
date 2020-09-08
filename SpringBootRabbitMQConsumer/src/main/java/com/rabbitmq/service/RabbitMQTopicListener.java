package com.rabbitmq.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rabbitmq.model.Employee;

@Service
public class RabbitMQTopicListener {

	@Value("${springboot.rabbitmq.queueNameTopicOne}")
	String queueNameTopicOne;

	@Value("${springboot.rabbitmq.queueNameTopicTwo}")
	String queueNameTopicTwo;

	@Value("${springboot.rabbitmq.queueNameTopicThree}")
	String queueNameTopicThree;
	
	@Value("${springboot.rabbitmq.queueNameTopicAllQueue}")
	String queueNameTopicAllQueue;

	@RabbitListener(queues = "${springboot.rabbitmq.queueNameTopicOne}")
	public void recievedMessageA(Employee employee) {
		System.out.println("Recieved Topic: " + employee);
		System.out.println("Topic queueNameTopicOne: " + queueNameTopicOne);
		System.out.println();
	}

	@RabbitListener(queues = "${springboot.rabbitmq.queueNameTopicTwo}")
	public void recievedMessageB(Employee employee) {
		System.out.println("Recieved Topic: " + employee);
		System.out.println("Topic queueNameTopicTwo: " + queueNameTopicTwo);
		System.out.println();
	}

	@RabbitListener(queues = "${springboot.rabbitmq.queueNameTopicThree}")
	public void recievedMessageC(Employee employee) {
		System.out.println("Recieved Topic: " + employee);
		System.out.println("Topic queueNameTopicThree: " + queueNameTopicThree);
		System.out.println();
	}

	@RabbitListener(queues = "${springboot.rabbitmq.queueNameTopicAllQueue}")
	public void recievedMessageD(Employee employee) {
		System.out.println("Recieved Topic: " + employee);
		System.out.println("Topic queueNameTopicAllQueue: " + queueNameTopicAllQueue);
		System.out.println();
	}
}