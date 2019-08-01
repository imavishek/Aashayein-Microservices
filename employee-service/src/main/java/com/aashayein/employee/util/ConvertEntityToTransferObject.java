/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.util
 * @FileName: ConvertEntityToTransferObject.java
 * @Author: Avishek Das
 * @CreatedDate: 26-06-2019
 * @Modified_By avishek.das @Last_On 26-Jun-2019 3:18:32 PM
 */

package com.aashayein.employee.util;

import org.springframework.stereotype.Component;

import com.aashayein.employee.dto.EmployeeTO;
import com.aashayein.employee.entities.Employee;

@Component
public class ConvertEntityToTransferObject {

	public EmployeeTO convertEmployeeToEmployeeTO(Employee employee) {

		EmployeeTO employeeTo = new EmployeeTO();

		if (employee != null) {
			employeeTo.setEmployeeId(employee.getEmployeeId());
			employeeTo.setEmployeeCode(employee.getEmployeeCode());
			employeeTo.setFirstName(employee.getFirstName());
			employeeTo.setMiddleName(employee.getMiddleName());
			employeeTo.setLastName(employee.getLastName());
			employeeTo.setFullName(employee.getFullName());
			employeeTo.setGender(employee.getGender());
			employeeTo.setMobileNumber(employee.getMobileNumber());
			employeeTo.setAlternateMobileNumber(employee.getAlternateMobileNumber());
			employeeTo.setEmail(employee.getEmail());
			employeeTo.setAlternateEmail(employee.getAlternateEmail());

			if (employee.getAddress() != null) {
				employeeTo.setCountryId(employee.getAddress().getCountry().getCountryId());
				employeeTo.setCountryName(employee.getAddress().getCountry().getCountryName());
				employeeTo.setStateId(employee.getAddress().getState().getStateId());
				employeeTo.setStateName(employee.getAddress().getState().getStateName());
				employeeTo.setCityId(employee.getAddress().getCity().getCityId());
				employeeTo.setCityName(employee.getAddress().getCity().getCityName());
				employeeTo.setPostalCode(employee.getAddress().getPostalCode());
				employeeTo.setAddressLine1(employee.getAddress().getAddressLine1());
			}

			employeeTo.setJobTitleId(employee.getTitle().getTitleId());
			employeeTo.setJobTitleName(employee.getTitle().getTitleName());
			employeeTo.setRoleId(employee.getRole().getRoleId());
			employeeTo.setRoleName(employee.getRole().getRoleName());
			employeeTo.setActive(employee.getActive());
			employeeTo.setArchive(employee.getArchive());
			employeeTo.setProfilePhoto(employee.getProfilePhoto());
			employeeTo.setJoiningDate(employee.getJoiningDate());
			employeeTo.setRecordCreated(employee.getRecordCreated());
			employeeTo.setRecordUpdated(employee.getRecordUpdated());
		}

		return employeeTo;
	}
}
