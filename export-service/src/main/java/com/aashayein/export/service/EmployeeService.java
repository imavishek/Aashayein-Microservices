/**
 * @ProjectName: export-service
 * @PackageName: com.aashayein.export.service
 * @FileName: EmployeeService.java
 * @Author: Avishek Das
 * @CreatedDate: 20-06-2019
 * @Modified_By avishek.das @Last_On 20-Jun-2019 12:18:46 AM
 */

package com.aashayein.export.service;

import java.net.URISyntaxException;
import java.util.List;

import com.aashayein.export.dto.EmployeeTO;

public interface EmployeeService {

	public List<EmployeeTO> getEmployees() throws URISyntaxException;

}
