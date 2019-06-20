/**
 * @ProjectName: export-service
 * @PackageName: com.aashayein.export.serviceImpl
 * @FileName: EmployeeServiceImpl.java
 * @Author: Avishek Das
 * @CreatedDate: 20-06-2019
 * @Modified_By avishek.das @Last_On 20-Jun-2019 12:20:22 AM
 */

package com.aashayein.export.serviceImpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.aashayein.export.dto.EmployeeTO;
import com.aashayein.export.dto.ExcelDetails;
import com.aashayein.export.service.EmployeeService;
import com.aashayein.export.util.ExcelWriter;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private ExcelWriter excelWriter;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public String exportEmployeesToExcel(HttpServletResponse response) throws URISyntaxException, IOException {

		String fileLocation = null;
		String fileName = "employees.xlsx";

		// Employee Service getEmployees url
		String baseUrl = "http://employee-service/Admin/Employee/getEmployees";
		URI url = new URI(baseUrl);

		// Getting employees from employee-service
		ResponseEntity<List<EmployeeTO>> responseDate = restTemplate.exchange(url, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<EmployeeTO>>() {
				});
		List<EmployeeTO> employees = responseDate.getBody();

		// Convert List<EmployeeTO> to List<Object>
		List<Object> employeeList = new ArrayList<Object>();
		employees.stream().forEach(employee -> employeeList.add((Object) employee));

		// Excel sheet columns
		String[] columns = { "Employee Code", "Name", "Gender", "Mobile No.", "Email Id", "Job Title", "Role",
				"Joining Date", "Archived", "Country", "State", "City", "PinCode", "Address" };

		// header style
		Map<String, String> headerStyle = new HashedMap<String, String>();
		headerStyle.put("font", "Arial");
		headerStyle.put("bold", "true");
		headerStyle.put("color", "BLACK");
		headerStyle.put("alignment", "CENTER");
		headerStyle.put("verticalAlignment", "CENTER");
		headerStyle.put("bottomBorder", "THIN");
		headerStyle.put("bottomBorderColor", "GREEN");

		// Record Not Found Style
		Map<String, String> recordNotFoundStyle = new HashedMap<String, String>();
		recordNotFoundStyle.put("font", "Arial");
		recordNotFoundStyle.put("bold", "true");
		recordNotFoundStyle.put("color", "RED");
		recordNotFoundStyle.put("alignment", "CENTER");
		recordNotFoundStyle.put("verticalAlignment", "CENTER");

		// Content Style
		Map<String, String> contentStyle = new HashedMap<String, String>();
		contentStyle.put("font", "Arial");
		contentStyle.put("color", "BLACK");
		contentStyle.put("alignment", "CENTER");
		contentStyle.put("verticalAlignment", "CENTER");

		// Date Cell Style
		Map<String, String> dateCellStyle = new HashedMap<String, String>();
		dateCellStyle.put("font", "Arial");
		dateCellStyle.put("color", "BLACK");
		dateCellStyle.put("alignment", "CENTER");
		dateCellStyle.put("verticalAlignment", "CENTER");
		dateCellStyle.put("dataFormat", "dd/MM/yyyy");

		// 7 no column should have the dateCellStyle
		Map<Integer, Map<String, String>> specificCellStyle = new HashedMap<Integer, Map<String, String>>();
		specificCellStyle.put(7, dateCellStyle);

		// Setting export excel details
		ExcelDetails excelDetails = new ExcelDetails();

		excelDetails.setSheetName("Employees");
		excelDetails.setColumns(columns);
		excelDetails.setHeaderStyle(headerStyle);
		excelDetails.setData(employeeList);
		excelDetails.setRecordNotFoundStyle(recordNotFoundStyle);
		excelDetails.setContentStyle(contentStyle);
		excelDetails.setSpecificCellStyle(specificCellStyle);

		// Export the employee excel sheet
		XSSFWorkbook workbook = excelWriter.buildWorkbook(excelDetails);

		FileOutputStream outputStream = null;
		try {
			File currDir = new File(".");
			String path = currDir.getAbsolutePath();
			fileLocation = path.substring(0, path.length() - 1) + "src\\main\\resources\\tmp\\" + fileName;

			outputStream = new FileOutputStream(fileLocation);
			workbook.write(outputStream);

			log.info("SpreedSheet Saved, Path : " + fileLocation);
		} catch (IOException ioe) {
			throw new RuntimeException("Error writing spreadsheet to output stream");
		} finally {
			if (workbook != null) {
				workbook.close();
			}
			if (outputStream != null) {
				outputStream.close();
			}
		}

		return fileLocation;
	}

}
