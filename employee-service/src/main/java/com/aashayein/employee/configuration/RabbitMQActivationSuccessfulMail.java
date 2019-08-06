/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.configuration
 * @FileName: RabbitMQActivationSuccessfulMail.java
 * @Author: Avishek Das
 * @CreatedDate: 05-08-2019
 * @Modified_By avishek.das @Last_On 05-Aug-2019 11:55:13 PM
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
public class RabbitMQActivationSuccessfulMail {

	@Value("${rabbit.employee.activation.successful.queue}")
	private String activationSuccessQueue;

	@Value("${rabbit.employee.activation.successful.exchange}")
	private String activationSuccessExchange;

	@Value("${rabbit.activation.successful.routing}")
	private String activationSuccessRouting;

	@Bean
	public Queue activationSuccessQueue() {
		return QueueBuilder.durable(activationSuccessQueue).build();
	}

	@Bean
	public TopicExchange activationSuccessExchange() {
		return new TopicExchange(activationSuccessExchange, true, false);
	}

	@Bean
	Binding bindActivationSuccessExchange() {
		return BindingBuilder.bind(activationSuccessQueue()).to(activationSuccessExchange()).with(activationSuccessRouting);
	}
}
