/**
 * @ProjectName: role-service
 * @PackageName: com.aashayein.role.service
 * @FileName: EmployeeRoleService.java
 * @Author: Avishek Das
 * @CreatedDate: 16-07-2019
 * @Modified_By avishek.das @Last_On 16-Jul-2019 9:08:37 PM
 */

package com.aashayein.role.service;

import java.util.List;

import com.aashayein.role.dto.EmployeeRoleTO;
import com.aashayein.role.exception.DatabindingException;

public interface EmployeeRoleService {

	List<EmployeeRoleTO> getEmployeeRoles();

	EmployeeRoleTO getEmployeeRoleById(Integer employeeRoleId);

	EmployeeRoleTO addEmployeeRole(EmployeeRoleTO employeeRoleTO);

	EmployeeRoleTO updateEmployeeRole(EmployeeRoleTO employeeRoleTO);

	EmployeeRoleTO deleteEmployeeRole(Integer employeeRoleId);

	EmployeeRoleTO activeEmployeeRole(Integer employeeRoleId);

	Boolean isValidEmployeeRoleId(String id) throws DatabindingException;

}
