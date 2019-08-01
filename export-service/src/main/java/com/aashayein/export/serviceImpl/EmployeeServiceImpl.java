/**
 * @ProjectName: export-service
 * @PackageName: com.aashayein.export.serviceImpl
 * @FileName: EmployeeServiceImpl.java
 * @Author: Avishek Das
 * @CreatedDate: 20-06-2019
 * @Modified_By avishek.das @Last_On 20-Jun-2019 12:20:22 AM
 */

package com.aashayein.export.serviceImpl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.aashayein.export.dto.EmployeeTO;
import com.aashayein.export.service.EmployeeService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import lombok.extern.slf4j.Slf4j;

@Service
@RefreshScope
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private TokenStore tokenStore;

	@Value("${url.employee-service.employees}")
	private String getEmployeesUrl;

//	@HystrixCommand(fallbackMethod = "getEmployeesFallback", commandProperties = {
//			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
//			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
//			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
//			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000") })
	@HystrixCommand(fallbackMethod = "getEmployeesFallback", commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE") })
	public List<EmployeeTO> getEmployees() throws URISyntaxException {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) auth.getDetails();
		OAuth2AccessToken accessToken = tokenStore.readAccessToken(details.getTokenValue());

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + accessToken.getValue());

		HttpEntity<String> entity = new HttpEntity<String>(headers);

		// Employee Service getEmployees url
		URI url = new URI(getEmployeesUrl);

		// Getting employees from employee-service
		ResponseEntity<List<EmployeeTO>> responseDate = restTemplate.exchange(url, HttpMethod.GET, entity,
				new ParameterizedTypeReference<List<EmployeeTO>>() {
				});
		List<EmployeeTO> employees = responseDate.getBody();

		return employees;
	}

	// Fallback method for getEmployees()
	public List<EmployeeTO> getEmployeesFallback() {

		log.info("Fallback - getEmployees");

		return new ArrayList<>();
	}

}
