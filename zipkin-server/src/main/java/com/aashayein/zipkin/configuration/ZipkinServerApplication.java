/**
 * @ProjectName: zipkin-server
 * @PackageName: com.aashayein.zipkin.configuration
 * @FileName: ZipkinServerApplication.java
 * @Author: Avishek Das
 * @CreatedDate: 08-06-2019
 * @Modified_By avishek.das @Last_On 08-Jun-2019 11:12:04 PM
 */

package com.aashayein.zipkin.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import zipkin.server.EnableZipkinServer;

@SpringBootApplication
@EnableEurekaClient
@EnableZipkinServer
public class ZipkinServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZipkinServerApplication.class, args);
	}

}
