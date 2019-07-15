/**
 * @ProjectName: title-service
 * @PackageName: com.aashayein.title.util
 * @FileName: ConvertEntityToTransferObject.java
 * @Author: Avishek Das
 * @CreatedDate: 15-07-2019
 * @Modified_By avishek.das @Last_On 15-Jul-2019 5:21:03 PM
 */

package com.aashayein.title.util;

import org.springframework.stereotype.Component;

import com.aashayein.title.dto.EmployeeTitleTO;
import com.aashayein.title.entities.EmployeeTitle;

@Component
public class ConvertEntityToTransferObject {

	public EmployeeTitleTO convertEmployeeTitleToEmployeeTitleTO(EmployeeTitle employeeTitle) {

		EmployeeTitleTO employeeTitleTO = new EmployeeTitleTO();

		if (employeeTitle != null) {
			employeeTitleTO.setTitleId(employeeTitle.getTitleId());
			employeeTitleTO.setTitleName(employeeTitle.getTitleName());
			employeeTitleTO.setArchive(employeeTitle.getArchive());
			employeeTitleTO.setRecordCreated(employeeTitle.getRecordCreated());
			employeeTitleTO.setRecordUpdated(employeeTitle.getRecordUpdated());
		}

		return employeeTitleTO;
	}
}
