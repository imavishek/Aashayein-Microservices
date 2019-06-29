/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.configuration
 * @FileName: RabbitMQConfiguration.java
 * @Author: Avishek Das
 * @CreatedDate: 28-06-2019
 * @Modified_By avishek.das @Last_On 28-Jun-2019 4:04:07 PM
 */

package com.aashayein.employee.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

	public static final String REGD_QUEUE = "regd-queue";
	public static final String REGD_EXCHANGE = "regd-exchange";
	public static final String REGD_ROUTING = "regd-routing";

	public static final String REGD_DEAD_QUEUE = "regd-dead-queue";
	public static final String REGD_DEAD_EXCHANGE = "regd-dead-exchange";
	public static final String REGD_DEAD_ROUTING = "regd-dead-routing";

	@Bean
	public Queue regdQueue() {
		return QueueBuilder.durable(REGD_QUEUE).withArgument("x-dead-letter-exchange", REGD_DEAD_EXCHANGE)
				.withArgument("x-dead-letter-routing-key", REGD_DEAD_ROUTING)
				// if message is not consumed in 60 seconds send to DLQ
				.withArgument("x-message-ttl", 10000).build();
	}

	@Bean
	public Exchange regdExchange() {
		return new TopicExchange(REGD_EXCHANGE, true, false);

		// return ExchangeBuilder.topicExchange(REGD_EXCHANGE).build();
	}

	@Bean
	Queue regdDeadQueue() {
		return QueueBuilder.durable(REGD_DEAD_QUEUE).build();
	}

	@Bean
	public Exchange regdDeadExchange() {
		return new TopicExchange(REGD_DEAD_EXCHANGE, true, false);
	}

	@Bean
	Binding bindRegdExchange(Queue regdQueue, TopicExchange regdExchange) {
		return BindingBuilder.bind(regdQueue).to(regdExchange).with(REGD_ROUTING);
	}

	@Bean
	Binding bindRegdDeadExchange(Queue regdDeadQueue, TopicExchange regdDeadExchange) {
		return BindingBuilder.bind(regdDeadQueue).to(regdDeadExchange).with(REGD_DEAD_ROUTING);
	}

	/* The message will be serialized as JSON instead of byte[] */
	@Bean
	public MessageConverter producerJackson2MessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
		return rabbitTemplate;
	}
}
