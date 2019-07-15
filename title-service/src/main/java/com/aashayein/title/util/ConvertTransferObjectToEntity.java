/**
 * @ProjectName: title-service
 * @PackageName: com.aashayein.title.util
 * @FileName: ConvertTransferObjectToEntity.java
 * @Author: Avishek Das
 * @CreatedDate: 15-07-2019
 * @Modified_By avishek.das @Last_On 15-Jul-2019 5:20:52 PM
 */

package com.aashayein.title.util;

import org.springframework.stereotype.Component;

import com.aashayein.title.dto.EmployeeTitleTO;
import com.aashayein.title.entities.EmployeeTitle;

@Component
public class ConvertTransferObjectToEntity {

	public EmployeeTitle convertEmployeeTitleTOToEmployeeTitle(EmployeeTitleTO employeeTitleTo) {
		EmployeeTitle employeeTitle = new EmployeeTitle();

		if (employeeTitleTo != null) {
			employeeTitle.setTitleName(employeeTitleTo.getTitleName());
		}

		return employeeTitle;
	}
}
