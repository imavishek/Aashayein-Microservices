/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.configuration
 * @FileName: SpringConfiguration.java
 * @Author: Avishek Das
 * @CreatedDate: 12-06-2019
 * @Modified_By avishek.das @Last_On 12-Jun-2019 11:58:18 AM
 */

package com.aashayein.employee.configuration;

import java.util.Locale;
import java.util.Map;

import javax.validation.Validator;

import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.HibernateValidatorConfiguration;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import com.google.common.collect.Maps;

@Configuration
// @EnableTransactionManagement
public class SpringConfiguration {

	@Bean("rest-template")
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	/*
	 * Ribbon is a client-side load balancer and SpringTemplate supports it by
	 * simply adding one annotation: @LoadBalanced.
	 */
	@Bean("ribbon-template")
	@LoadBalanced
	public RestTemplate restTemplateWithRibbon() {
		return new RestTemplate();
	}

	@Bean(name = "filterMultipartResolver")
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(10000000); // Max 10Mb file
		multipartResolver.setDefaultEncoding("UTF-8");
		return multipartResolver;
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages/messages");
		messageSource.setDefaultEncoding("UTF-8");
		// Only for development environment
		messageSource.setCacheSeconds(1); // Load all the changes in properties file withoutrestart server

		return messageSource;
	}

	@Bean
	@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
	@ConditionalOnMissingBean(Validator.class)
	public static LocalValidatorFactoryBean defaultValidator(final MessageSource messageSource) {
		LocalValidatorFactoryBean factoryBean = new LocalValidatorFactoryBean();
		factoryBean.setProviderClass(HibernateValidator.class);
		factoryBean.setValidationMessageSource(messageSource);
		Map<String, String> validationProperties = Maps.newHashMap();
		validationProperties.put(HibernateValidatorConfiguration.FAIL_FAST, "true");
		factoryBean.setValidationPropertyMap(validationProperties);
		return factoryBean;
	}

	@Bean
	public LocaleResolver localeResolver() {
		CookieLocaleResolver localeResolver = new CookieLocaleResolver();
		localeResolver.setDefaultLocale(new Locale("en"));

		return localeResolver;
	}

	// Adding BCryptPasswordEncoder
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
