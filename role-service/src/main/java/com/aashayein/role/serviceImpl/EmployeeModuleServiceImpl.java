/**
 * @ProjectName: role-service
 * @PackageName: com.aashayein.role.serviceImpl
 * @FileName: EmployeeModuleServiceImpl.java
 * @Author: Avishek Das
 * @CreatedDate: 16-07-2019
 * @Modified_By avishek.das @Last_On 16-Jul-2019 10:13:45 PM
 */

package com.aashayein.role.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aashayein.role.dto.EmployeeModuleTO;
import com.aashayein.role.entities.EmployeeModule;
import com.aashayein.role.repository.EmployeeModuleRepository;
import com.aashayein.role.service.EmployeeModuleService;
import com.aashayein.role.util.ConvertEntityToTransferObject;

@Service
public class EmployeeModuleServiceImpl implements EmployeeModuleService {

	@Autowired
	private EmployeeModuleRepository employeeModuleRepository;

	@Autowired
	private ConvertEntityToTransferObject convertEntityToTransferObject;

	@Override
	@Transactional
	public List<EmployeeModuleTO> getEmployeeModules() {
		List<EmployeeModuleTO> employeeModules = new ArrayList<EmployeeModuleTO>();

		List<EmployeeModule> employeeModule = employeeModuleRepository.findByOrderByModuleNameAsc();

		if (!employeeModule.isEmpty()) {
			for (EmployeeModule module : employeeModule) {
				employeeModules.add(convertEntityToTransferObject.convertEmployeeModuleToEmployeeModuleTO(module));
			}
		}

		return employeeModules;
	}

}
