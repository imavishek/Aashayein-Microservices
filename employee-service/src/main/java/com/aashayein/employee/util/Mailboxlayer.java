/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.util
 * @FileName: Mailboxlayer.java
 * @Author: Avishek Das
 * @CreatedDate: 26-06-2019
 * @Modified_By avishek.das @Last_On 26-Jun-2019 9:30:57 PM
 */

package com.aashayein.employee.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.aashayein.employee.dto.MailCheckerTO;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Mailboxlayer {

	@Autowired
	@Qualifier("rest-template")
	private RestTemplate restTemplate;

	@Value("${api.mailbox.access.key}")
	private String mailBoxlayerKey;

	@Value("${api.mailbox.url}")
	private String mailBoxlayerUrl;

	// Calling Mailboxlayer API for check email existence
	public MailCheckerTO checkEmailExistence(String mailId) {

		String url = mailBoxlayerUrl + "?access_key=" + mailBoxlayerKey + "&email=" + mailId + "&smtp=1";

		log.info("Mailboxlayer API Url: " + url);

		ResponseEntity<MailCheckerTO> response = restTemplate.exchange(url, HttpMethod.GET, null, MailCheckerTO.class);

		log.info("Mailboxlayer API Response: " + response);

		return response.getBody();
	}
}
