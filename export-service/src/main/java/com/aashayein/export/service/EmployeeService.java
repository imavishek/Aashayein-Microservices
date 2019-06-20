/**
 * @ProjectName: export-service
 * @PackageName: com.aashayein.export.service
 * @FileName: EmployeeService.java
 * @Author: Avishek Das
 * @CreatedDate: 20-06-2019
 * @Modified_By avishek.das @Last_On 20-Jun-2019 12:18:46 AM
 */

package com.aashayein.export.service;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletResponse;

public interface EmployeeService {

	String exportEmployeesToExcel(HttpServletResponse response) throws URISyntaxException, IOException;
}
