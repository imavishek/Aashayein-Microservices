/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.rabbitMQ_sender
 * @FileName: RegistrationMailSender.java
 * @Author: Avishek Das
 * @CreatedDate: 28-06-2019
 * @Modified_By avishek.das @Last_On 28-Jun-2019 5:35:00 PM
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
public class RegistrationMailSender {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Value("${rabbit.employee.registration.queue.name}")
	private String regdQueue;

	@Value("${rabbit.employee.registration.exchange.name}")
	private String regdExchange;

	@Value("${rabbit.registration.routing.name}")
	private String regdRouting;

	public void sendMail(Object event) throws JsonProcessingException {
		rabbitTemplate.convertAndSend(regdExchange, regdRouting, event);
		log.info("Message (Registration Mail) Published Successfully");
		log.info(
				"Sending event message to Exchange '" + regdExchange + "' with Json: " + event2PrettyJsonString(event));
	}

	private String event2PrettyJsonString(Object event) throws JsonProcessingException {
		return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(event);
	}
}
