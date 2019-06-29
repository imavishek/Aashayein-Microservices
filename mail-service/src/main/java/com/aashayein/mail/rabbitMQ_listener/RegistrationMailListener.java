/**
 * @ProjectName: mail-service
 * @PackageName: com.aashayein.mail.rabbitMQ_listener
 * @FileName: RegistrationMailListener.java
 * @Author: Avishek Das
 * @CreatedDate: 29-06-2019
 * @Modified_By avishek.das @Last_On 29-Jun-2019 1:52:24 PM
 */

package com.aashayein.mail.rabbitMQ_listener;

import java.io.IOException;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.aashayein.mail.configuration.RabbitMQConfiguration;
import com.aashayein.mail.dto.EmployeeTO;
import com.aashayein.mail.dto.MailRequestTO;
import com.aashayein.mail.util.MailUtil;
import com.rabbitmq.client.Channel;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RegistrationMailListener {

	@Autowired
	private MailUtil mailUtil;

	@RabbitListener(queues = RabbitMQConfiguration.REGD_QUEUE)
	public void sendMail(@Payload EmployeeTO employee, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag)
			throws IOException {
		log.info("Message (Registration Mail) Consumed Successfully. Message: " + employee.toString());

		Boolean success = sendRegistrationSuccessMail(employee);

		if (success) {
			channel.basicAck(tag, false);
			log.info("Registration Mail Acknowledged");
		} else {
			channel.basicReject(tag, true);
			log.info("Do Not Discard (Requeue) Registration Mail");
		}
	}

	private Boolean sendRegistrationSuccessMail(EmployeeTO employee) {

		String confirmationUrl = null;
		MailRequestTO mailRequestTo = new MailRequestTO();

		confirmationUrl = "http://localhost:8052/api/login-service/setPassword?token=" + employee.getTokenUUID();

		mailRequestTo.setRecipientName(employee.getFirstName());
		mailRequestTo.setEmailTo(employee.getEmail());
		mailRequestTo.setEmailSubject("Aashayein - Active Account");
		mailRequestTo.setEmailForm("aashayein2019@gmail.com");
		mailRequestTo.setEmailTemplateName("registration-success.ftl");
		mailRequestTo.setUrl(confirmationUrl);
		mailRequestTo.setDetails(employee);

		log.info("Mail Sending... Subject: " + mailRequestTo.getEmailSubject());

		return mailUtil.sendEmail(mailRequestTo);
	}
}
