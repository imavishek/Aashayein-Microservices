/**
 * @ProjectName: mail-service
 * @PackageName: com.aashayein.mail.util
 * @FileName: MailUtil.java
 * @Author: Avishek Das
 * @CreatedDate: 29-06-2019
 * @Modified_By avishek.das @Last_On 29-Jun-2019 3:26:31 PM
 */

package com.aashayein.mail.util;

import java.io.File;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.aashayein.mail.dto.MailRequestTO;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MailUtil {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private Configuration config;

	public Boolean sendEmail(MailRequestTO mailRequest) {

		Boolean success = false;
		String rootPath = "";
		MimeMessage mimeMessage = mailSender.createMimeMessage();

		File currDir = new File(".");
		String path = currDir.getAbsolutePath();
		rootPath = path.substring(0, path.length() - 1) + "src\\main\\resources\\images\\logos\\";

		try {
			// set mediaType
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

			Template template = config.getTemplate(mailRequest.getEmailTemplateName());
			String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, mailRequest);

			helper.setTo(new InternetAddress(mailRequest.getEmailTo()));
			helper.setFrom(new InternetAddress(mailRequest.getEmailForm()));
			helper.setText(html, true);
			helper.addInline("logo", new File(rootPath + "/Aashayein.png"));
			helper.addInline("google", new File(rootPath + "/ico_google.png"));
			helper.addInline("facebook", new File(rootPath + "/ico_facebook.png"));
			helper.addInline("twitter", new File(rootPath + "/ico_twitter.png"));
			helper.addInline("instagram", new File(rootPath + "/ico_instagram.png"));
			helper.addInline("linkedin", new File(rootPath + "/ico_linkedin.png"));
			helper.setSubject(mailRequest.getEmailSubject());

			mailSender.send(mimeMessage);

			log.info("Mail Sent Successfully. To:- " + mailRequest.getRecipientName() + "<" + mailRequest.getEmailTo()
					+ ">," + " Subject: " + mailRequest.getEmailSubject());
			success = true;

		} catch (MessagingException | IOException | TemplateException e) {

			log.error("Fail To Send Mail. To:- " + mailRequest.getRecipientName() + "<" + mailRequest.getEmailTo()
					+ ">," + " Subject:-" + mailRequest.getEmailSubject());

			log.error(e.getMessage() + " [Exception " + e.getClass() + "]");

			success = false;
		}

		return success;
	}
}
