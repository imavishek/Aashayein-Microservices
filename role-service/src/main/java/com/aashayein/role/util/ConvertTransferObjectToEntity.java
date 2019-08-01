/**
 * @ProjectName: role-service
 * @PackageName: com.aashayein.role.util
 * @FileName: ConvertTransferObjectToEntity.java
 * @Author: Avishek Das
 * @CreatedDate: 16-07-2019
 * @Modified_By avishek.das @Last_On 16-Jul-2019 9:12:29 PM
 */

package com.aashayein.role.util;

import org.springframework.stereotype.Component;

import com.aashayein.role.dto.EmployeeRoleTO;
import com.aashayein.role.entities.EmployeeRole;

@Component
public class ConvertTransferObjectToEntity {

	public EmployeeRole convertEmployeeRoleTOToEmployeeRole(EmployeeRoleTO employeeRoleTo) {
		EmployeeRole employeeRole = new EmployeeRole();

		if (employeeRoleTo != null) {
			employeeRole.setRoleName(employeeRoleTo.getRoleName());
		}

		return employeeRole;
	}
}
