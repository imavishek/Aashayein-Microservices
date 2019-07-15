/**
 * @ProjectName: title-service
 * @PackageName: com.aashayein.title.service
 * @FileName: EmployeeTitleService.java
 * @Author: Avishek Das
 * @CreatedDate: 14-07-2019
 * @Modified_By avishek.das @Last_On 14-Jul-2019 2:10:49 PM
 */

package com.aashayein.title.service;

import java.util.List;

import com.aashayein.title.dto.EmployeeTitleTO;
import com.aashayein.title.exception.DatabindingException;

public interface EmployeeTitleService {

	List<EmployeeTitleTO> getJobTitles();

	EmployeeTitleTO getEmployeeTitleById(Integer employeeTitleId);

	EmployeeTitleTO addEmployeeTitle(EmployeeTitleTO employeeTitleTo);

	EmployeeTitleTO updateEmployeeTitle(EmployeeTitleTO employeeTitleTo);

	EmployeeTitleTO deleteEmployeeTitle(Integer employeeTitleId);

	EmployeeTitleTO activeEmployeeTitle(Integer employeeTitleId);

	Boolean isValidEmployeeTitleId(String id) throws DatabindingException;
}
