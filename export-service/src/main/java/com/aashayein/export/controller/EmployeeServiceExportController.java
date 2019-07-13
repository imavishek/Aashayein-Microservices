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
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aashayein.export.service.EmployeeExportService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/Admin/Employee")
@Slf4j
public class EmployeeServiceExportController {

	@Autowired
	private EmployeeExportService employeeExportService;

	@GetMapping(value = "/exportEmployeesToExcel")
	public void exportEmployeesToExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String fileLocation = employeeExportService.exportEmployeesToExcel();
		File file = new File(fileLocation);

		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment; filename=" + file.getName());

		OutputStream out = response.getOutputStream();
		FileInputStream in = new FileInputStream(file);

		IOUtils.copy(in, out);

		log.info("Employee SpreedSheet Downloaded successfully");

		out.close();
		in.close();
		file.delete();

		/*
		 * InputStreamResource resource = new InputStreamResource(new
		 * FileInputStream(file)); String fileName = file.getName(); Long fileLength =
		 * file.length();
		 * 
		 * return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
		 * "attachment;filename=" + fileName) .contentLength(fileLength).body(resource);
		 */

	}
}
