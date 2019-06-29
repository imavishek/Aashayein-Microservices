/**
 * @ProjectName: mail-service
 * @PackageName: com.aashayein.mail.configuration
 * @FileName: MailServiceApplication.java
 * @Author: Avishek Das
 * @CreatedDate: 29-06-2019
 * @Modified_By avishek.das @Last_On 29-Jun-2019 1:41:02 PM
 */

package com.aashayein.mail.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(scanBasePackages = { "com.aashayein.mail.*" })
@EnableEurekaClient
@EnableCircuitBreaker
@PropertySource({ "classpath:properties/application.properties", "classpath:properties/bootstrap.properties" })
public class MailServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MailServiceApplication.class, args);
	}
}
