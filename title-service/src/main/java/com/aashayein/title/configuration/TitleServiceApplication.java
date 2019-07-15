/**
 * @ProjectName: title-service
 * @PackageName: com.aashayein.title.configuration
 * @FileName: TitleServiceApplication.java
 * @Author: Avishek Das
 * @CreatedDate: 14-07-2019
 * @Modified_By avishek.das @Last_On 14-Jul-2019 2:16:32 PM
 */

package com.aashayein.title.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = { "com.aashayein.title.*" })
@EntityScan(basePackages = { "com.aashayein.title.entities" })
@EnableJpaRepositories(basePackages = { "com.aashayein.title.repository" })
@EnableJpaAuditing
@EnableEurekaClient
@EnableCircuitBreaker
public class TitleServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TitleServiceApplication.class, args);
	}

}