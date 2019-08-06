/**
 * @ProjectName: mail-service
 * @PackageName: com.aashayein.mail.configuration
 * @FileName: SpringConfiguration.java
 * @Author: Avishek Das
 * @CreatedDate: 29-06-2019
 * @Modified_By avishek.das @Last_On 29-Jun-2019 1:41:58 PM
 */

package com.aashayein.mail.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SpringConfiguration {

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	// Adding Freemarker Template Engines
	@Primary
	@Bean
	public FreeMarkerConfigurationFactoryBean factoryBean() {
		FreeMarkerConfigurationFactoryBean freeMarkerConfigurationFactoryBean = new FreeMarkerConfigurationFactoryBean();
		freeMarkerConfigurationFactoryBean.setTemplateLoaderPath("classpath:templates/");

		return freeMarkerConfigurationFactoryBean;
	}

}
