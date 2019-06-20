/**
 * @ProjectName: export-service
 * @PackageName: com.aashayein.export.util
 * @FileName: BuildSpreadSheet.java
 * @Author: Avishek Das
 * @CreatedDate: 20-06-2019
 * @Modified_By avishek.das @Last_On 20-Jun-2019 12:39:11 PM
 */

package com.aashayein.export.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.stereotype.Component;

import com.aashayein.export.dto.EmployeeTO;

@Component
public class BuildSpreadSheet {

	public XSSFSheet buildSheetForEmployees(XSSFSheet sheet, List<Object> employeeList) {

		Integer rowNumber = 1;
		Row row = null;

		// Convert List<Object> to List<EmployeeTO>
		List<EmployeeTO> employees = new ArrayList<EmployeeTO>();
		employeeList.stream().forEach(employee -> employees.add((EmployeeTO) employee));

		for (EmployeeTO employee : employees) {
			row = sheet.createRow(rowNumber++);

			row.createCell(0).setCellValue(employee.getEmployeeCode());
			row.createCell(1).setCellValue(employee.getFullName());
			row.createCell(2).setCellValue(employee.getGender());
			row.createCell(3).setCellValue(employee.getMobileNumber());
			row.createCell(4).setCellValue(employee.getEmail());
			row.createCell(5).setCellValue(employee.getJobTitleName());
			row.createCell(6).setCellValue(employee.getRoleName());
			row.createCell(7).setCellValue(employee.getJoiningDate());
			row.createCell(8).setCellValue((employee.getArchive() == 1) ? "Yes" : "No");
			row.createCell(9).setCellValue((employee.getCountryName() == null) ? "N/A" : employee.getCountryName());
			row.createCell(10).setCellValue((employee.getStateName() == null) ? "N/A" : employee.getStateName());
			row.createCell(11).setCellValue((employee.getCityName() == null) ? "N/A" : employee.getCityName());
			row.createCell(12).setCellValue((employee.getPostalCode() == null) ? "N/A" : employee.getPostalCode());
			row.createCell(13).setCellValue((employee.getAddressLine1() == null) ? "N/A" : employee.getAddressLine1());
		}

		return sheet;
	}
}
