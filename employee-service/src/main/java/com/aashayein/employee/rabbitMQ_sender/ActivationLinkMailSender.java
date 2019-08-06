/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.rabbitMQ_sender
 * @FileName: ActivationLinkMailSender.java
 * @Author: Avishek Das
 * @CreatedDate: 06-08-2019
 * @Modified_By avishek.das @Last_On 06-Aug-2019 1:29:38 PM
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
public class ActivationLinkMailSender {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Value("${rabbit.employee.activation.link.queue}")
	private String activationLinkQueue;

	@Value("${rabbit.employee.activation.link.exchange}")
	private String activationLinkExchange;

	@Value("${rabbit.activation.link.routing}")
	private String activationLinkRouting;

	public void sendMail(Object event) throws JsonProcessingException {
		rabbitTemplate.convertAndSend(activationLinkExchange, activationLinkRouting, event);
		log.info("Message (Activation Link Mail) Published Successfully");
		log.info("Sending event message to Exchange '" + activationLinkExchange + "' with Json: "
				+ event2PrettyJsonString(event));
	}

	private String event2PrettyJsonString(Object event) throws JsonProcessingException {
		return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(event);
	}
}
