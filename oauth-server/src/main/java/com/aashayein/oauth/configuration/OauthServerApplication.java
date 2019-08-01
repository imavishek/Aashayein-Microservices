/**
 * @ProjectName: oauth-server
 * @PackageName: com.aashayein.oauth.configuration
 * @FileName: OauthServerApplication.java
 * @Author: Avishek Das
 * @CreatedDate: 24-07-2019
 * @Modified_By avishek.das @Last_On 24-Jul-2019 12:30:05 AM
 */

package com.aashayein.oauth.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@EnableAuthorizationServer
@SpringBootApplication(scanBasePackages = { "com.aashayein.oauth.*" })
@EntityScan(basePackages = { "com.aashayein.oauth.entities" })
@EnableJpaRepositories(basePackages = { "com.aashayein.oauth.repository" })
@EnableJpaAuditing
@EnableEurekaClient
public class OauthServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OauthServerApplication.class, args);
	}

}
