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
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.aashayein.export.dto.EmployeeTO;
import com.aashayein.export.service.EmployeeService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private RestTemplate restTemplate;

//	@HystrixCommand(fallbackMethod = "getEmployeesFallback", commandProperties = {
//			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
//			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
//			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
//			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000") })
	@HystrixCommand(fallbackMethod = "getEmployeesFallback")
	public List<EmployeeTO> getEmployees() throws URISyntaxException {

		// Employee Service getEmployees url
		String baseUrl = "http://employee-service/Admin/Employee/getEmployees";
		URI url = new URI(baseUrl);

		// Getting employees from employee-service
		ResponseEntity<List<EmployeeTO>> responseDate = restTemplate.exchange(url, HttpMethod.GET, null,
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
