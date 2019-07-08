/**
 * @ProjectName: export-service
 * @PackageName: com.aashayein.export.configuration
 * @FileName: ExportServiceApplication.java
 * @Author: Avishek Das
 * @CreatedDate: 19-06-2019
 * @Modified_By avishek.das @Last_On 19-Jun-2019 6:08:17 PM
 */

package com.aashayein.export.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(scanBasePackages = { "com.aashayein.export.*" })
@EnableEurekaClient
@EnableCircuitBreaker
public class ExportServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExportServiceApplication.class, args);
	}

}
