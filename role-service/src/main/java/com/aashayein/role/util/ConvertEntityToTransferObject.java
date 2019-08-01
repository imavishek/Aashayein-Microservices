/**
 * @ProjectName: role-service
 * @PackageName: com.aashayein.role.util
 * @FileName: ConvertEntityToTransferObject.java
 * @Author: Avishek Das
 * @CreatedDate: 16-07-2019
 * @Modified_By avishek.das @Last_On 16-Jul-2019 9:12:17 PM
 */

package com.aashayein.role.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.aashayein.role.dto.EmployeeModuleTO;
import com.aashayein.role.dto.EmployeeRoleTO;
import com.aashayein.role.entities.EmployeeModule;
import com.aashayein.role.entities.EmployeeRole;

@Component
public class ConvertEntityToTransferObject {

	public EmployeeRoleTO convertEmployeeRoleToEmployeeRoleTO(EmployeeRole employeeRole) {

		EmployeeRoleTO employeeRoleTo = new EmployeeRoleTO();
		List<EmployeeModuleTO> accessModules = new ArrayList<EmployeeModuleTO>();

		if (employeeRole != null) {
			employeeRoleTo.setRoleId(employeeRole.getRoleId());
			employeeRoleTo.setRoleName(employeeRole.getRoleName());
			employeeRoleTo.setArchive(employeeRole.getArchive());

			if (employeeRole.getModules() != null && !employeeRole.getModules().isEmpty()) {
				for (EmployeeModule module : employeeRole.getModules()) {
					accessModules.add(convertEmployeeModuleToEmployeeModuleTO(module));
				}

				employeeRoleTo.setAccessModules(accessModules);
			}

			employeeRoleTo.setRecordCreated(employeeRole.getRecordCreated());
			employeeRoleTo.setRecordUpdated(employeeRole.getRecordUpdated());
		}

		return employeeRoleTo;
	}

	public EmployeeModuleTO convertEmployeeModuleToEmployeeModuleTO(EmployeeModule employeeModule) {
		EmployeeModuleTO employeeModuleTo = new EmployeeModuleTO();

		if (employeeModule != null) {
			employeeModuleTo.setModuleId(employeeModule.getModuleId());
			employeeModuleTo.setModuleName(employeeModule.getModuleName());
			employeeModuleTo.setModuleDirectory(employeeModule.getModuleDirectory());
			employeeModuleTo.setArchive(employeeModule.getArchive());
			employeeModuleTo.setRecordCreated(employeeModule.getRecordCreated());
			employeeModuleTo.setRecordUpdated(employeeModule.getRecordUpdated());
		}

		return employeeModuleTo;
	}
}
