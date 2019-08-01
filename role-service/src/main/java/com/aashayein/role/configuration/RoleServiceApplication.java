/**
 * @ProjectName: role-service
 * @PackageName: com.aashayein.role.configuration
 * @FileName: RoleServiceApplication.java
 * @Author: Avishek Das
 * @CreatedDate: 16-07-2019
 * @Modified_By avishek.das @Last_On 16-Jul-2019 8:48:31 PM
 */

package com.aashayein.role.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.aashayein.role.configuration.RoleServiceApplication;

@SpringBootApplication(scanBasePackages = { "com.aashayein.role.*" })
@EntityScan(basePackages = { "com.aashayein.role.entities" })
@EnableJpaRepositories(basePackages = { "com.aashayein.role.repository" })
@EnableJpaAuditing
@EnableEurekaClient
@EnableCircuitBreaker
public class RoleServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoleServiceApplication.class, args);
	}
}
