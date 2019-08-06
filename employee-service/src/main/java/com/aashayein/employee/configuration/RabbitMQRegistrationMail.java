/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.configuration
 * @FileName: RabbitMQRegistrationMail.java
 * @Author: Avishek Das
 * @CreatedDate: 05-08-2019
 * @Modified_By avishek.das @Last_On 05-Aug-2019 11:54:04 PM
 */

package com.aashayein.employee.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RefreshScope
public class RabbitMQRegistrationMail {

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
	public TopicExchange regdExchange() {
		return new TopicExchange(regdExchange, true, false);

		// return ExchangeBuilder.topicExchange(regdExchange).build();
	}

	@Bean
	Queue regdDeadQueue() {
		return QueueBuilder.durable(regdDeadQueue).build();
	}

	@Bean
	public TopicExchange regdDeadExchange() {
		return new TopicExchange(regdDeadExchange, true, false);
	}

	@Bean
	Binding bindRegdExchange() {
		return BindingBuilder.bind(regdQueue()).to(regdExchange()).with(regdRouting);
	}

	@Bean
	Binding bindRegdDeadExchange() {
		return BindingBuilder.bind(regdDeadQueue()).to(regdDeadExchange()).with(regdDeadRouting);
	}
}
