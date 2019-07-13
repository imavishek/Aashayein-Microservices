/**
 * @ProjectName: address-service
 * @PackageName: com.aashayein.address.configuration
 * @FileName: AddressServiceApplication.java
 * @Author: Avishek Das
 * @CreatedDate: 05-07-2019
 * @Modified_By avishek.das @Last_On 05-Jul-2019 4:39:27 PM
 */

package com.aashayein.address.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = { "com.aashayein.address.*" })
@EntityScan(basePackages = { "com.aashayein.address.entities" })
@EnableJpaRepositories(basePackages = { "com.aashayein.address.repository" })
@EnableJpaAuditing
@EnableEurekaClient
@EnableCircuitBreaker
public class AddressServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AddressServiceApplication.class, args);
	}
}
