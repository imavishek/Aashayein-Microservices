/**
 * @ProjectName: role-service
 * @PackageName: com.aashayein.role.controller
 * @FileName: EmployeeModuleController.java
 * @Author: Avishek Das
 * @CreatedDate: 16-07-2019
 * @Modified_By avishek.das @Last_On 16-Jul-2019 10:10:33 PM
 */

package com.aashayein.role.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aashayein.role.dto.EmployeeModuleTO;
import com.aashayein.role.service.EmployeeModuleService;

@RestController
@RequestMapping(value = "/Admin/EmployeeModule")
public class EmployeeModuleController {

	@Autowired
	private EmployeeModuleService employeeModuleService;

	// Get all employee modules
	@GetMapping(value = "/getModules")
	public List<EmployeeModuleTO> getEmployeeModules() {

		return employeeModuleService.getEmployeeModules();

	}
}
