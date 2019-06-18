/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.serviceImpl
 * @FileName: EmployeeServiceImpl.java
 * @Author: Avishek Das
 * @CreatedDate: 12-06-2019
 * @Modified_By avishek.das @Last_On 12-Jun-2019 3:58:51 PM
 */

package com.aashayein.employee.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aashayein.employee.dto.EmployeeTO;
import com.aashayein.employee.entities.Employee;
import com.aashayein.employee.repository.EmployeeRepository;
import com.aashayein.employee.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	@Transactional
	public List<EmployeeTO> getAllEmployees() {

		List<EmployeeTO> employees = new ArrayList<EmployeeTO>();

		List<Employee> employee = employeeRepository.findByOrderByRecordCreatedDesc();

		if (!employee.isEmpty()) {
			for (Employee emp : employee) {
				EmployeeTO employeeTo = new EmployeeTO();

				employeeTo.setEmployeeId(emp.getEmployeeId());
				employeeTo.setEmployeeCode(emp.getEmployeeCode());
				employeeTo.setFirstName(emp.getFirstName());
				employeeTo.setMiddleName(emp.getMiddleName());
				employeeTo.setLastName(emp.getLastName());
				employeeTo.setFullName(emp.getFullName());
				employeeTo.setGender(emp.getGender());
				employeeTo.setMobileNumber(emp.getMobileNumber());
				employeeTo.setAlternateMobileNumber(emp.getAlternateMobileNumber());
				employeeTo.setEmail(emp.getEmail());
				employeeTo.setAlternateEmail(emp.getAlternateEmail());
				employeeTo.setJobTitleName(emp.getTitle().getTitleName());
				employeeTo.setRoleName(emp.getRole().getRoleName());

				if (emp.getAddress() != null) {
					employeeTo.setCountryName(emp.getAddress().getCountry().getCountryName());
					employeeTo.setStateName(emp.getAddress().getState().getStateName());
					employeeTo.setCityName(emp.getAddress().getCity().getCityName());
					employeeTo.setPostalCode(emp.getAddress().getPostalCode());
					employeeTo.setAddressLine1(emp.getAddress().getAddressLine1());
				}

				employeeTo.setActive(emp.getActive());
				employeeTo.setArchive(emp.getArchive());
				employeeTo.setProfilePhoto(emp.getProfilePhoto());
				employeeTo.setJoiningDate(emp.getJoiningDate());
				employeeTo.setRecordCreated(emp.getRecordCreated());
				employeeTo.setRecordUpdated(emp.getRecordUpdated());

				employees.add(employeeTo);
			}
		}
		return employees;
	}

	@Override
	@Transactional
	public EmployeeTO getEmployeeById(Integer employeeId) {
		return null;
	}

	@Override
	@Transactional
	public EmployeeTO getEmployeeByEmail(String email) {
		return null;
	}

}
