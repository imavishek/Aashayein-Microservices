/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.configuration
 * @FileName: RabbitMQActivationLinkMail.java
 * @Author: Avishek Das
 * @CreatedDate: 05-08-2019
 * @Modified_By avishek.das @Last_On 05-Aug-2019 11:54:43 PM
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
public class RabbitMQActivationLinkMail {

	@Value("${rabbit.employee.activation.link.queue}")
	private String activationLinkQueue;

	@Value("${rabbit.employee.activation.link.exchange}")
	private String activationLinkExchange;

	@Value("${rabbit.activation.link.routing}")
	private String activationLinkRouting;

	@Bean
	public Queue activationLinkQueue() {
		return QueueBuilder.durable(activationLinkQueue).build();
	}

	@Bean
	public TopicExchange activationLinkExchange() {
		return new TopicExchange(activationLinkExchange, true, false);
	}

	@Bean
	Binding bindActivationLinkExchange() {
		return BindingBuilder.bind(activationLinkQueue()).to(activationLinkExchange()).with(activationLinkRouting);
	}
}
