/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.configuration
 * @FileName: RabbitMQConfiguration.java
 * @Author: Avishek Das
 * @CreatedDate: 28-06-2019
 * @Modified_By avishek.das @Last_On 28-Jun-2019 4:04:07 PM
 */

package com.aashayein.employee.configuration;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class RabbitMQConfiguration {

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
