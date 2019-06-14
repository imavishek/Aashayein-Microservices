/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.controller
 * @FileName: EmployeeController.java
 * @Author: Avishek Das
 * @CreatedDate: 12-06-2019
 * @Modified_By avishek.das @Last_On 12-Jun-2019 12:13:49 PM
 */

package com.aashayein.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aashayein.employee.dto.EmployeeTO;
import com.aashayein.employee.service.EmployeeService;

@RestController
@RequestMapping(value = "/Admin/Employee")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	// private static final Logger log =
	// LoggerFactory.getLogger(EmployeeController.class);

	@GetMapping(value = "/getEmployees")
	public List<EmployeeTO> getEmployees() {

		List<EmployeeTO> employees = employeeService.getAllEmployees();

		return employees;
	}

}
