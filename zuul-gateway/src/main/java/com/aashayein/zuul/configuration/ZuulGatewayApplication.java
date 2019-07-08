/**
 * @ProjectName: zuul-gateway
 * @PackageName: com.aashayein.zuul.configuration
 * @FileName: ZuulGatewayApplication.java
 * @Author: Avishek Das
 * @CreatedDate: 06-06-2019
 * @Modified_By avishek.das @Last_On 06-Jun-2019 9:46:22 PM
 */

package com.aashayein.zuul.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication(scanBasePackages = { "com.aashayein.zuul.*" })
@EnableZuulProxy
@EnableEurekaClient
// @PropertySource({ "classpath:properties/application.properties", "classpath:properties/bootstrap.properties" })
public class ZuulGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulGatewayApplication.class, args);
	}

}
