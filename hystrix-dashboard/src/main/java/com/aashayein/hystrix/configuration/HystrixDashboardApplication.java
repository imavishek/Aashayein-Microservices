/**
 * @ProjectName: hystrix-dashboard
 * @PackageName: com.aashayein.hystrix.configuration
 * @FileName: HystrixDashboardApplication.java
 * @Author: Avishek Das
 * @CreatedDate: 08-06-2019
 * @Modified_By avishek.das @Last_On 08-Jun-2019 1:17:42 AM
 */

package com.aashayein.hystrix.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringBootApplication
@EnableEurekaClient
@EnableHystrixDashboard
@EnableTurbine
public class HystrixDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(HystrixDashboardApplication.class, args);
	}

}
