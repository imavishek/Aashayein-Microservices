/**
 * @ProjectName: role-service
 * @PackageName: com.aashayein.role.serviceImpl
 * @FileName: EmployeeRoleService.java
 * @Author: Avishek Das
 * @CreatedDate: 16-07-2019
 * @Modified_By avishek.das @Last_On 16-Jul-2019 9:09:03 PM
 */

package com.aashayein.role.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aashayein.role.dto.EmployeeModuleTO;
import com.aashayein.role.dto.EmployeeRoleTO;
import com.aashayein.role.entities.EmployeeRole;
import com.aashayein.role.entities.EmployeeRoleAccess;
import com.aashayein.role.entities.RoleId_ModuleId;
import com.aashayein.role.exception.DatabindingException;
import com.aashayein.role.repository.EmployeeRoleAccessRepository;
import com.aashayein.role.repository.EmployeeRoleRepository;
import com.aashayein.role.service.EmployeeRoleService;
import com.aashayein.role.util.ConvertEntityToTransferObject;
import com.aashayein.role.util.ConvertTransferObjectToEntity;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeRoleServiceImpl implements EmployeeRoleService {

	@Autowired
	private EmployeeRoleRepository employeeRoleRepository;

	@Autowired
	private EmployeeRoleAccessRepository employeeRoleAccessRepository;

	@Autowired
	private ConvertEntityToTransferObject convertEntityToTransferObject;

	@Autowired
	private ConvertTransferObjectToEntity convertTransferObjectToEntity;

	@Override
	@Transactional
	public List<EmployeeRoleTO> getEmployeeRoles() {
		List<EmployeeRoleTO> employeeRoles = new ArrayList<EmployeeRoleTO>();

		List<EmployeeRole> employeeRole = employeeRoleRepository.findByOrderByRoleNameAsc();

		if (!employeeRole.isEmpty()) {
			for (EmployeeRole role : employeeRole) {
				employeeRoles.add(convertEntityToTransferObject.convertEmployeeRoleToEmployeeRoleTO(role));
			}
		}

		return employeeRoles;
	}

	@Override
	@Transactional
	public EmployeeRoleTO getEmployeeRoleById(Integer employeeRoleId) {
		EmployeeRole employeeRole = null;
		EmployeeRoleTO employeeRoleTO = null;

		employeeRole = employeeRoleRepository.findByRoleId(employeeRoleId);

		if (employeeRole != null) {
			employeeRoleTO = convertEntityToTransferObject.convertEmployeeRoleToEmployeeRoleTO(employeeRole);
		}

		return employeeRoleTO;
	}

	@Override
	@Transactional
	public EmployeeRoleTO addEmployeeRole(EmployeeRoleTO employeeRoleTO) {

		EmployeeRole role = convertTransferObjectToEntity.convertEmployeeRoleTOToEmployeeRole(employeeRoleTO);

		role = employeeRoleRepository.save(role);

		addEmployeeRoleAccess(role.getRoleId(), employeeRoleTO.getAccessModules());

		return convertEntityToTransferObject.convertEmployeeRoleToEmployeeRoleTO(role);
	}

	@Override
	@Transactional
	public EmployeeRoleTO updateEmployeeRole(EmployeeRoleTO employeeRoleTo) {
		EmployeeRole employeeRole = null;

		employeeRole = employeeRoleRepository.findByRoleId(employeeRoleTo.getRoleId());

		if (employeeRole == null)
			return null;

		employeeRole.setRoleName(employeeRoleTo.getRoleName());
		employeeRole = employeeRoleRepository.save(employeeRole);

		deleteEmployeeRoleAccess(employeeRoleTo.getRoleId());
		addEmployeeRoleAccess(employeeRoleTo.getRoleId(), employeeRoleTo.getAccessModules());

		employeeRoleTo = convertEntityToTransferObject.convertEmployeeRoleToEmployeeRoleTO(employeeRole);

		return employeeRoleTo;
	}

	@Override
	@Transactional
	public EmployeeRoleTO deleteEmployeeRole(Integer employeeRoleId) {
		EmployeeRole employeeRole = null;
		EmployeeRoleTO employeeRoleTO = null;

		employeeRole = employeeRoleRepository.findByRoleId(employeeRoleId);

		if (employeeRole != null) {
			employeeRole.setArchive((byte) 1);
			employeeRole = employeeRoleRepository.save(employeeRole);

			employeeRoleTO = convertEntityToTransferObject.convertEmployeeRoleToEmployeeRoleTO(employeeRole);
		}

		return employeeRoleTO;
	}

	@Override
	@Transactional
	public EmployeeRoleTO activeEmployeeRole(Integer employeeRoleId) {
		EmployeeRole employeeRole = null;
		EmployeeRoleTO employeeRoleTO = null;

		employeeRole = employeeRoleRepository.findByRoleId(employeeRoleId);

		if (employeeRole != null) {
			employeeRole.setArchive((byte) 0);
			employeeRole = employeeRoleRepository.save(employeeRole);

			employeeRoleTO = convertEntityToTransferObject.convertEmployeeRoleToEmployeeRoleTO(employeeRole);
		}

		return employeeRoleTO;
	}

	@Override
	public Boolean isValidEmployeeRoleId(String id) throws DatabindingException {
		Boolean valid = false;
		List<String> messages = new ArrayList<String>();

		try {
			Integer.parseInt(id);
			valid = true;
		} catch (NumberFormatException e) {
			log.error("Invalid EmployeeRole Id");
			messages.add("Invalid EmployeeRole Id");

			// Throw Databinding Exception with error messages
			throw new DatabindingException(messages);
		}

		return valid;

	}

	private void addEmployeeRoleAccess(Integer roleId, List<EmployeeModuleTO> accessModules) {
		List<EmployeeRoleAccess> employeeRoleAccessObject = new ArrayList<EmployeeRoleAccess>();

		for (EmployeeModuleTO moduleTo : accessModules) {

			EmployeeRoleAccess employeeRoleAccess = new EmployeeRoleAccess();
			RoleId_ModuleId roleId_ModuleId = new RoleId_ModuleId();

			roleId_ModuleId.setRoleId(roleId);
			roleId_ModuleId.setModuleId(moduleTo.getModuleId());
			employeeRoleAccess.setRoleId_ModuleId(roleId_ModuleId);

			employeeRoleAccessObject.add(employeeRoleAccess);
		}

		for (EmployeeRoleAccess employeeRoleAccess : employeeRoleAccessObject) {
			employeeRoleAccessRepository.save(employeeRoleAccess);
		}
	}

	private void deleteEmployeeRoleAccess(Integer roleId) {
		employeeRoleAccessRepository.deleteByRoleId(roleId);
	}

}
