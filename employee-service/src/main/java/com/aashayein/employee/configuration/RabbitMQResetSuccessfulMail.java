/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.configuration
 * @FileName: RabbitMQResetSuccessfulMail.java
 * @Author: Avishek Das
 * @CreatedDate: 05-08-2019
 * @Modified_By avishek.das @Last_On 05-Aug-2019 11:55:56 PM
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
public class RabbitMQResetSuccessfulMail {

	@Value("${rabbit.employee.reset.successful.queue}")
	private String resetSuccessQueue;

	@Value("${rabbit.employee.reset.successful.exchange}")
	private String resetSuccessExchange;

	@Value("${rabbit.reset.successful.routing}")
	private String resetSuccessRouting;

	@Bean
	public Queue resetSuccessQueue() {
		return QueueBuilder.durable(resetSuccessQueue).build();
	}

	@Bean
	public TopicExchange resetSuccessExchange() {
		return new TopicExchange(resetSuccessExchange, true, false);
	}

	@Bean
	Binding bindResetSuccessExchange() {
		return BindingBuilder.bind(resetSuccessQueue()).to(resetSuccessExchange()).with(resetSuccessRouting);
	}
}
