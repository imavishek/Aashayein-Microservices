/**
 * @ProjectName: role-service
 * @PackageName: com.aashayein.role.service
 * @FileName: EmployeeModuleService.java
 * @Author: Avishek Das
 * @CreatedDate: 16-07-2019
 * @Modified_By avishek.das @Last_On 16-Jul-2019 10:12:26 PM
 */

package com.aashayein.role.service;

import java.util.List;

import com.aashayein.role.dto.EmployeeModuleTO;

public interface EmployeeModuleService {

	List<EmployeeModuleTO> getEmployeeModules();

}
