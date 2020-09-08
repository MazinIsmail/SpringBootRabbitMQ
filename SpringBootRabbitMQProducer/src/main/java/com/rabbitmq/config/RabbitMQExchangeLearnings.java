package com.rabbitmq.config;

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
 */
public class RabbitMQExchangeLearnings {

}
