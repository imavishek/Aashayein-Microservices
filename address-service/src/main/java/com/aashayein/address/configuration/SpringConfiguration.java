/**
 * @ProjectName: address-service
 * @PackageName: com.aashayein.address.configuration
 * @FileName: SpringConfiguration.java
 * @Author: Avishek Das
 * @CreatedDate: 05-07-2019
 * @Modified_By avishek.das @Last_On 05-Jul-2019 4:40:18 PM
 */

package com.aashayein.address.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SpringConfiguration {

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
