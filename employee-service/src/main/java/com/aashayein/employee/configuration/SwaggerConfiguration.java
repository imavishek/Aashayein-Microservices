/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.command
 * @FileName: SwaggerConfiguration.java
 * @Author: Avishek Das
 * @CreatedDate: 02-08-2019
 * @Modified_By avishek.das @Last_On 02-Aug-2019 9:35:07 PM
 */

package com.aashayein.employee.configuration;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.aashayein.employee.controller"))
				.paths(PathSelectors.any()).build().apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfo("Employee Service Rest Api", "Employee Details Available", "v1.0", "Terms of service",
				new Contact("Avishek Das", "https://github.com/imavishek", "aashayein@gmail.com"), null, null,
				Collections.emptyList());
	}

//	@Override
//	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
//
//		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
//	}

}
