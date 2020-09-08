package com.rabbitmq.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rabbitmq.model.Employee;

@Service
public class RabbitMQFanoutListener {

	@Value("${springboot.rabbitmq.queueNameFanoutOne}")
	String queueNameFanoutOne;

	@Value("${springboot.rabbitmq.queueNameFanoutTwo}")
	String queueNameFanoutTwo;

	@Value("${springboot.rabbitmq.queueNameFanoutThree}")
	String queueNameFanoutThree;

	@RabbitListener(queues = "${springboot.rabbitmq.queueNameFanoutOne}")
	public void recievedMessageA(Employee employee) {
		System.out.println("Recieved Fanout: " + employee);
		System.out.println("Fanout queueNameFanoutOne: " + queueNameFanoutOne);
		System.out.println();
	}

	@RabbitListener(queues = "${springboot.rabbitmq.queueNameFanoutTwo}")
	public void recievedMessageB(Employee employee) {
		System.out.println("Recieved Fanout: " + employee);
		System.out.println("Fanout queueNameFanoutTwo: " + queueNameFanoutTwo);
		System.out.println();
	}

	@RabbitListener(queues = "${springboot.rabbitmq.queueNameFanoutThree}")
	public void recievedMessageC(Employee employee) {
		System.out.println("Recieved Fanout: " + employee);
		System.out.println("Fanout queueNameFanoutThree: " + queueNameFanoutThree);
		System.out.println();
	}
}