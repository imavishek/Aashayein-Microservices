/**
 * @ProjectName: eureka-server
 * @PackageName: com.aashayein.eureka.configuration
 * @FileName: EurekaServerApplication.java
 * @Author: Avishek Das
 * @CreatedDate: 06-06-2019
 * @Modified_By avishek.das @Last_On 06-Jun-2019 11:41:38 AM
 */

package com.aashayein.eureka.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerApplication.class, args);
	}

}
