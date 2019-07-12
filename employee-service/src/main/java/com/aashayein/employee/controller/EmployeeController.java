/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.controller
 * @FileName: EmployeeController.java
 * @Author: Avishek Das
 * @CreatedDate: 12-06-2019
 * @Modified_By avishek.das @Last_On 12-Jun-2019 12:13:49 PM
 */

package com.aashayein.employee.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aashayein.employee.dto.EmployeeTO;
import com.aashayein.employee.exception.DatabindingException;
import com.aashayein.employee.exception.EmployeeNotFoundException;
import com.aashayein.employee.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/Admin/Employee")
@Slf4j
@RefreshScope
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@Value("${path.server}")
	private String demo;
	
	@Value("${spring.profiles.active}")
	private String activeProfile;

	// Get all employee details
	@GetMapping(value = "/getEmployees")
	public List<EmployeeTO> getEmployees() {

		List<EmployeeTO> employees = employeeService.getAllEmployees();

		return employees;
	}

	// Get employee by employeeId
	@GetMapping(value = "/getEmployeeById/{id}")
	public EmployeeTO getEmployeeById(@PathVariable("id") String id)
			throws DatabindingException, EmployeeNotFoundException {

		EmployeeTO employee = null;
		Integer employeeId = null;
		List<String> messages = new ArrayList<String>();

		try {
			employeeId = Integer.parseInt(id);
			employee = employeeService.getEmployeeById(employeeId);

			if (employee == null) {
				throw new EmployeeNotFoundException(employeeId.toString());
			}
		} catch (NumberFormatException e) {
			log.error("Invalid Employee Id");
			messages.add("Invalid Employee Id");

			// Throw Databinding Exception with error messages
			throw new DatabindingException(messages);
		}

		return employee;
	}

	// Get all employee details
	@GetMapping(value = "/demo")
	public String getDemo() {


		return demo;
	}

}
