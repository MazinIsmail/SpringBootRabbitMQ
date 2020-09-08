package com.rabbitmq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rabbitmq.model.Employee;
import com.rabbitmq.service.RabbitMQDirectSender;
import com.rabbitmq.service.RabbitMQFanoutSender;
import com.rabbitmq.service.RabbitMQTopicSender;

@RestController
@RequestMapping(value = "/producer")
public class RabbitMQController {

	@Autowired
	RabbitMQDirectSender rabbitMQDirectSender;

	@Autowired
	RabbitMQFanoutSender rabbitMQFanoutSender;

	@Autowired
	RabbitMQTopicSender rabbitMQTopicSender;

	// http://localhost:8082/producer/direct
	@GetMapping(value = "/direct")
	public String producerDirect() {
		Employee emp = new Employee();
		emp.setEmpId("202020");
		emp.setEmpName("Mazin Ismail");
		rabbitMQDirectSender.sendDirect(emp);
		return "Message sent to the RabbitMQ JavaInUse Successfully";
	}

	// http://localhost:8082/producer/fanout
	@GetMapping(value = "/fanout")
	public String producerFanout() {
		Employee emp = new Employee();
		emp.setEmpId("202020");
		emp.setEmpName("Mazin Ismail");
		rabbitMQFanoutSender.sendFanout(emp);
		return "Message sent to the RabbitMQ JavaInUse Successfully";
	}

	// http://localhost:8082/producer/topic
	@GetMapping(value = "/topic")
	public String producerTopic() {
		Employee emp = new Employee();
		emp.setEmpId("202020");
		emp.setEmpName("Mazin Ismail");
		rabbitMQTopicSender.sendTopic(emp);
		return "Message sent to the RabbitMQ JavaInUse Successfully";
	}

}
