/**
 * @ProjectName: mail-service
 * @PackageName: com.aashayein.mail.configuration
 * @FileName: RabbitMQConfiguration.java
 * @Author: Avishek Das
 * @CreatedDate: 29-06-2019
 * @Modified_By avishek.das @Last_On 29-Jun-2019 1:44:54 PM
 */

package com.aashayein.mail.configuration;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.messaging.handler.annotation.support.MessageHandlerMethodFactory;

@Configuration
@EnableRabbit
public class RabbitMQConfiguration implements RabbitListenerConfigurer {

	@Autowired
	public ConnectionFactory connectionFactory;

	public static final String REGD_QUEUE = "regd-queue";
	public static final String RESET_SUCCESSFUL_QUEUE = "reset-successful-queue";
	public static final String ACTIVATION_SUCCESSFUL_QUEUE = "activation-successful-queue";
	public static final String RESET_LINK_QUEUE = "reset-link-queue";
	public static final String ACTIVATION_LINK_QUEUE = "activation-link-queue";

	@Override
	public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
		registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactoryBean());
	}

	@Bean
	@Primary
	MessageHandlerMethodFactory messageHandlerMethodFactoryBean() {
		DefaultMessageHandlerMethodFactory messageHandlerMethodFactory = new DefaultMessageHandlerMethodFactory();
		messageHandlerMethodFactory.setMessageConverter(consumerJackson2MessageConverter());
		return messageHandlerMethodFactory;
	}

	@Bean
	public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
		return new MappingJackson2MessageConverter();
	}

	@Bean
	public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory);
		factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
		factory.setConcurrentConsumers(3);
		factory.setMaxConcurrentConsumers(10);
		return factory;
	}

}
