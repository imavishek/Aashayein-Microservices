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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@RefreshScope
public class RabbitMQConfiguration {

	@Value("${rabbit.employee.registration.queue.name}")
	private String regdQueue;

	@Value("${rabbit.employee.registration.exchange.name}")
	private String regdExchange;

	@Value("${rabbit.registration.routing.name}")
	private String regdRouting;

	@Value("${rabbit.employee.registration.dead.queue.name}")
	private String regdDeadQueue;

	@Value("${rabbit.employee.registration.dead.exchange.name}")
	private String regdDeadExchange;

	@Value("${rabbit.registration.dead.routing.name}")
	private String regdDeadRouting;

	@Bean
	public Queue regdQueue() {
		return QueueBuilder.durable(regdQueue).withArgument("x-dead-letter-exchange", regdDeadExchange)
				.withArgument("x-dead-letter-routing-key", regdDeadRouting)
				// if message is not consumed in 60 seconds send to DLQ
				.withArgument("x-message-ttl", 10000).build();
	}

	@Bean
	public Exchange regdExchange() {
		return new TopicExchange(regdExchange, true, false);

		// return ExchangeBuilder.topicExchange(regdExchange).build();
	}

	@Bean
	Queue regdDeadQueue() {
		return QueueBuilder.durable(regdDeadQueue).build();
	}

	@Bean
	public Exchange regdDeadExchange() {
		return new TopicExchange(regdDeadExchange, true, false);
	}

	@Bean
	Binding bindRegdExchange(Queue regdQueue, TopicExchange regdExchange) {
		return BindingBuilder.bind(regdQueue).to(regdExchange).with(regdRouting);
	}

	@Bean
	Binding bindRegdDeadExchange(Queue regdDeadQueue, TopicExchange regdDeadExchange) {
		return BindingBuilder.bind(regdDeadQueue).to(regdDeadExchange).with(regdDeadRouting);
	}

	/* The message will be serialized as JSON instead of byte[] */
	@Bean
	public MessageConverter producerJackson2MessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	@Primary
	public RabbitTemplate rabbitMQTemplate(final ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
		return rabbitTemplate;
	}
}
