/**
 * @ProjectName: export-service
 * @PackageName: com.aashayein.export.configuration
 * @FileName: SpringConfiguration.java
 * @Author: Avishek Das
 * @CreatedDate: 19-06-2019
 * @Modified_By avishek.das @Last_On 19-Jun-2019 6:09:17 PM
 */

package com.aashayein.export.configuration;

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
