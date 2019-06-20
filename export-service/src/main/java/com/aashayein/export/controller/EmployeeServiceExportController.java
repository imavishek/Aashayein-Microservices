/**
 * @ProjectName: export-service
 * @PackageName: com.aashayein.export.controller
 * @FileName: EmployeeServiceExportController.java
 * @Author: Avishek Das
 * @CreatedDate: 19-06-2019
 * @Modified_By avishek.das @Last_On 19-Jun-2019 6:18:23 PM
 */

package com.aashayein.export.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aashayein.export.service.EmployeeService;

@RestController
@RequestMapping(value = "/Admin/Employee")
public class EmployeeServiceExportController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping(value = "/exportEmployeesToExcel")
	public ResponseEntity<InputStreamResource> exportEmployeesToExcel(HttpServletRequest request,
			HttpServletResponse response) throws URISyntaxException, IOException {

		String fileLocation = employeeService.exportEmployeesToExcel(response);

		File file = new File(fileLocation);
		InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

		String fileName = file.getName();
		Long fileLength = file.length();

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName)
				.contentLength(fileLength).body(resource);
	}
}
