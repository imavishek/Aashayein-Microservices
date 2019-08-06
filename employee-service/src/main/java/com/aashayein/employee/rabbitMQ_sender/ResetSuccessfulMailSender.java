/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.rabbitMQ_sender
 * @FileName: ResetSuccessfulMailSender.java
 * @Author: Avishek Das
 * @CreatedDate: 06-08-2019
 * @Modified_By avishek.das @Last_On 06-Aug-2019 1:30:35 PM
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
public class ResetSuccessfulMailSender {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Value("${rabbit.employee.reset.successful.queue}")
	private String resetSuccessQueue;

	@Value("${rabbit.employee.reset.successful.exchange}")
	private String resetSuccessExchange;

	@Value("${rabbit.reset.successful.routing}")
	private String resetSuccessRouting;

	public void sendMail(Object event) throws JsonProcessingException {
		rabbitTemplate.convertAndSend(resetSuccessExchange, resetSuccessRouting, event);
		log.info("Message (Reset Password Success Mail) Published Successfully");
		log.info("Sending event message to Exchange '" + resetSuccessExchange + "' with Json: "
				+ event2PrettyJsonString(event));
	}

	private String event2PrettyJsonString(Object event) throws JsonProcessingException {
		return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(event);
	}
}
