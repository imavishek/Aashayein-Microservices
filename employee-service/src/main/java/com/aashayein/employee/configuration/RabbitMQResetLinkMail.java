/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.configuration
 * @FileName: RabbitMQResetLinkMail.java
 * @Author: Avishek Das
 * @CreatedDate: 05-08-2019
 * @Modified_By avishek.das @Last_On 05-Aug-2019 11:55:35 PM
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
public class RabbitMQResetLinkMail {

	@Value("${rabbit.employee.reset.link.queue}")
	private String resetLinkQueue;

	@Value("${rabbit.employee.reset.link.exchange}")
	private String resetLinkExchange;

	@Value("${rabbit.reset.link.routing}")
	private String resetLinkRouting;

	@Bean
	public Queue resetLinkQueue() {
		return QueueBuilder.durable(resetLinkQueue).build();
	}

	@Bean
	public TopicExchange resetLinkExchange() {
		return new TopicExchange(resetLinkExchange, true, false);
	}

	@Bean
	Binding bindResetLinkExchange() {
		return BindingBuilder.bind(resetLinkQueue()).to(resetLinkExchange()).with(resetLinkRouting);
	}
}
