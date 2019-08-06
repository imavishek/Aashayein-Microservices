/**
 * @ProjectName: mail-service
 * @PackageName: com.aashayein.mail.rabbitMQ_listener
 * @FileName: ResetSuccessfulMailListener.java
 * @Author: Avishek Das
 * @CreatedDate: 05-08-2019
 * @Modified_By avishek.das @Last_On 05-Aug-2019 11:28:19 PM
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
public class ResetSuccessfulMailListener {

	@Autowired
	private MailUtil mailUtil;

	@RabbitListener(queues = RabbitMQConfiguration.RESET_SUCCESSFUL_QUEUE)
	public void sendMail(@Payload EmployeeTO employee, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag)
			throws IOException {
		log.info("Message (Reset Successful Mail) Consumed Successfully. Message: " + employee.toString());

		Boolean success = sendResetSuccessfulMail(employee);

		if (success) {
			channel.basicAck(tag, false);
			log.info("Reset Successful Mail Acknowledged");
		} else {
			channel.basicReject(tag, true);
			log.info("Do Not Discard (Requeue) Reset Successful Mail");
		}
	}

	private Boolean sendResetSuccessfulMail(EmployeeTO employee) {

		MailRequestTO mailRequestTo = new MailRequestTO();

		mailRequestTo.setRecipientName(employee.getFirstName());
		mailRequestTo.setEmailTo(employee.getEmail());
		mailRequestTo.setEmailSubject("Aashayein - Password Reset");
		mailRequestTo.setEmailForm("aashayein2019@gmail.com");
		mailRequestTo.setEmailTemplateName("reset-password-success.ftl");
		mailRequestTo.setDetails(employee);

		log.info("Mail Sending... Subject: " + mailRequestTo.getEmailSubject());

		return mailUtil.sendEmail(mailRequestTo);
	}
}
