/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.rabbitMQ_sender
 * @FileName: ActivationSuccessfulMailSender.java
 * @Author: Avishek Das
 * @CreatedDate: 06-08-2019
 * @Modified_By avishek.das @Last_On 06-Aug-2019 1:29:55 PM
 */

package com.aashayein.employee.rabbitMQ_sender;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RefreshScope
public class ActivationSuccessfulMailSender {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Value("${rabbit.employee.activation.successful.queue}")
	private String activationSuccessQueue;

	@Value("${rabbit.employee.activation.successful.exchange}")
	private String activationSuccessExchange;

	@Value("${rabbit.activation.successful.routing}")
	private String activationSuccessRouting;

	public void sendMail(Object event) throws JsonProcessingException {
		rabbitTemplate.convertAndSend(activationSuccessExchange, activationSuccessRouting, event);
		log.info("Message (Activation Success Mail) Published Successfully");
		log.info("Sending event message to Exchange '" + activationSuccessExchange + "' with Json: "
				+ event2PrettyJsonString(event));
	}

	private String event2PrettyJsonString(Object event) throws JsonProcessingException {
		return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(event);
	}
}
