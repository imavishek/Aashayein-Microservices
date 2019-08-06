/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.rabbitMQ_sender
 * @FileName: ResetLinkMailSender.java
 * @Author: Avishek Das
 * @CreatedDate: 06-08-2019
 * @Modified_By avishek.das @Last_On 06-Aug-2019 1:30:19 PM
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
public class ResetLinkMailSender {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Value("${rabbit.employee.reset.link.queue}")
	private String resetLinkQueue;

	@Value("${rabbit.employee.reset.link.exchange}")
	private String resetLinkExchange;

	@Value("${rabbit.reset.link.routing}")
	private String resetLinkRouting;

	public void sendMail(Object event) throws JsonProcessingException {
		rabbitTemplate.convertAndSend(resetLinkExchange, resetLinkRouting, event);
		log.info("Message (Reset Password Link Mail) Published Successfully");
		log.info("Sending event message to Exchange '" + resetLinkExchange + "' with Json: "
				+ event2PrettyJsonString(event));
	}

	private String event2PrettyJsonString(Object event) throws JsonProcessingException {
		return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(event);
	}
}
