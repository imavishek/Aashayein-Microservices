/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.configuration
 * @FileName: EmployeeServiceApplication.java
 * @Author: Avishek Das
 * @CreatedDate: 12-06-2019
 * @Modified_By avishek.das @Last_On 12-Jun-2019 11:57:20 AM
 */

package com.aashayein.employee.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = { "com.aashayein.employee.*" })
@EntityScan(basePackages = { "com.aashayein.employee.entities" })
@EnableJpaRepositories(basePackages = { "com.aashayein.employee.repository" })
@EnableJpaAuditing
@EnableEurekaClient
@EnableCircuitBreaker
@PropertySource({ "classpath:properties/application.properties", "classpath:properties/bootstrap.properties" })
public class EmployeeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceApplication.class, args);
	}

}